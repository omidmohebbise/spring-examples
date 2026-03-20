from __future__ import annotations

import os
from typing import Literal

import torch
from pydantic import BaseModel, Field
from transformers import AutoModelForCausalLM, AutoTokenizer


MODEL_ID = os.getenv("HF_MODEL_ID", "microsoft/Phi-3-mini-4k-instruct")


class ChatMessage(BaseModel):
    role: Literal["system", "user", "assistant"]
    content: str


class ChatCompletionRequest(BaseModel):
    model: str | None = None
    messages: list[ChatMessage]
    temperature: float = 0.7
    max_tokens: int = Field(default=256, ge=1, le=4096)
    stream: bool = False


class LlmService:
    def __init__(self, model_id: str = MODEL_ID) -> None:
        self.model_id = model_id
        print(f"Loading model: {self.model_id}")

        self.tokenizer = AutoTokenizer.from_pretrained(self.model_id)

        self.model = AutoModelForCausalLM.from_pretrained(
            self.model_id,
            torch_dtype=torch.float16 if torch.cuda.is_available() else torch.float32,
            device_map="auto",
        )

        if self.tokenizer.pad_token is None:
            self.tokenizer.pad_token = self.tokenizer.eos_token

    def build_prompt(self, messages: list[ChatMessage]) -> str:
        parts: list[str] = []

        for message in messages:
            if message.role == "system":
                parts.append(f"System: {message.content}")
            elif message.role == "user":
                parts.append(f"User: {message.content}")
            elif message.role == "assistant":
                parts.append(f"Assistant: {message.content}")

        parts.append("Assistant:")
        return "\n".join(parts)

    def generate(self, req: ChatCompletionRequest) -> dict:
        prompt = self.build_prompt(req.messages)

        inputs = self.tokenizer(prompt, return_tensors="pt").to(self.model.device)
        prompt_token_count = int(inputs["input_ids"].shape[1])

        with torch.no_grad():
            output_ids = self.model.generate(
                **inputs,
                max_new_tokens=req.max_tokens,
                temperature=req.temperature,
                do_sample=req.temperature > 0,
                pad_token_id=self.tokenizer.eos_token_id,
            )

        generated_ids = output_ids[0][prompt_token_count:]
        answer = self.tokenizer.decode(generated_ids, skip_special_tokens=True).strip()

        completion_tokens = int(len(generated_ids))
        total_tokens = prompt_token_count + completion_tokens

        return {
            "answer": answer,
            "prompt_tokens": prompt_token_count,
            "completion_tokens": completion_tokens,
            "total_tokens": total_tokens,
            "model": self.model_id,
        }
