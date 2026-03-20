from __future__ import annotations

import os
import time
from typing import Any, Literal

from fastapi import FastAPI, HTTPException
from pydantic import BaseModel

from llm_service import ChatCompletionRequest, LlmService


HOST = os.getenv("HOST", "0.0.0.0")
PORT = int(os.getenv("PORT", "8000"))

app = FastAPI(title="Local Hugging Face OpenAI-Compatible Chat API")
llm_service = LlmService()


class ChatCompletionChoiceMessage(BaseModel):
    role: Literal["assistant"] = "assistant"
    content: str


class ChatCompletionChoice(BaseModel):
    index: int
    message: ChatCompletionChoiceMessage
    finish_reason: str


class Usage(BaseModel):
    prompt_tokens: int
    completion_tokens: int
    total_tokens: int


class ChatCompletionResponse(BaseModel):
    id: str
    object: str = "chat.completion"
    created: int
    model: str
    choices: list[ChatCompletionChoice]
    usage: Usage


@app.get("/health")
def health() -> dict[str, str]:
    return {"status": "ok", "model": llm_service.model_id}


@app.get("/v1/models")
def list_models() -> dict[str, Any]:
    return {
        "object": "list",
        "data": [
            {
                "id": llm_service.model_id,
                "object": "model",
                "owned_by": "local-huggingface",
            }
        ],
    }


@app.post("/v1/chat/completions")
def chat_completions(req: ChatCompletionRequest) -> ChatCompletionResponse:
    if req.stream:
        raise HTTPException(status_code=400, detail="Streaming is not implemented in this demo.")

    if not req.messages:
        raise HTTPException(status_code=400, detail="messages must not be empty")

    result = llm_service.generate(req)

    return ChatCompletionResponse(
        id=f"chatcmpl-{int(time.time() * 1000)}",
        created=int(time.time()),
        model=result["model"],
        choices=[
            ChatCompletionChoice(
                index=0,
                message=ChatCompletionChoiceMessage(content=result["answer"]),
                finish_reason="stop",
            )
        ],
        usage=Usage(
            prompt_tokens=result["prompt_tokens"],
            completion_tokens=result["completion_tokens"],
            total_tokens=result["total_tokens"],
        ),
    )


if __name__ == "__main__":
    import uvicorn
    uvicorn.run("app:app", host=HOST, port=PORT, reload=False)
