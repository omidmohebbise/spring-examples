# 🧠 Life Principles RAG System — Architecture & Responsibilities

## 📌 Overview

This project is a **Retrieval-Augmented Generation (RAG)** system that delivers age-based life advice.

It combines:
- Spring Boot (API layer)
- Spring AI (orchestration)
- PostgreSQL + pgvector (semantic search)
- Hugging Face model (served via Python FastAPI)
- LLM (final answer generation)

---

## 🧱 High-Level Architecture

```
User → Spring Controller → Spring AI → pgvector → Spring AI → Python LLM → Response
```

---

## 🔧 Components & Responsibilities

| Component | Responsibility |
|----------|---------------|
| Spring Boot | REST API, request handling |
| Spring AI | Orchestrates embeddings, retrieval, prompt construction |
| PostgreSQL | Stores documents |
| pgvector | Performs similarity search on embeddings |
| Python FastAPI | Exposes LLM as OpenAI-compatible API |
| Hugging Face Model | Generates responses |
| LlmService (Python) | Handles model loading and inference |

---

## 🧠 Responsibilities Breakdown

### 🟦 Spring AI
- Converts text → embeddings
- Sends queries to vector store
- Applies metadata filters (age group)
- Builds prompt with retrieved context
- Sends prompt to LLM

---

### 🟩 pgvector
- Stores embeddings
- Computes similarity between vectors
- Returns nearest neighbors (Top-K)
- Uses indexing for performance

**Important:**  
pgvector does NOT understand meaning — only distance.

---

### 🟨 Python FastAPI (Your `app.py`)
- Acts as an **LLM gateway**
- Implements OpenAI-compatible API:
  - `/v1/chat/completions`
  - `/v1/models`
- Delegates actual generation to `LlmService`

---

### 🟪 LlmService (Python)
- Loads Hugging Face model
- Builds prompt from messages
- Runs inference
- Returns generated text + token usage

---

### 🟥 LLM (Hugging Face Model)
- Understands language
- Generates final response
- Uses provided context (from RAG)

---

## 🔄 End-to-End Flow

1. User sends request with age + question
2. Spring AI embeds the question
3. pgvector retrieves relevant documents
4. Spring AI builds prompt with context
5. Prompt is sent to Python FastAPI
6. LLM generates answer
7. Response returned to user

---

## 📦 Python API Role

Your FastAPI app provides:

### Health Check
```
GET /health
```

### Model Listing
```
GET /v1/models
```

### Chat Completion
```
POST /v1/chat/completions
```

This mimics OpenAI — allowing Spring AI to reuse its OpenAI client.

---

## ⚖️ Responsibility Summary

| Layer | Responsibility |
|------|---------------|
| pgvector | Similarity search |
| Spring AI | Retrieval + prompt building |
| FastAPI | API layer for LLM |
| LlmService | Model execution |
| LLM | Answer generation |

---

## 🔁 Alternatives

### 🧠 LLM Providers

| Tool | Type | Notes |
|------|------|------|
| OpenAI | Managed API | Easy, high quality |
| Hugging Face | Flexible | Self-host or API |
| Anthropic | API | Strong reasoning |
| Google Gemini | API | Ecosystem integration |

---

### 🗃️ Vector Databases

| Tool | Type | Notes |
|------|------|------|
| pgvector | PostgreSQL extension | Simple, embedded |
| Pinecone | Managed | Scalable |
| Weaviate | Vector DB | Hybrid search |
| Qdrant | Vector DB | Good filtering |
| Milvus | Open-source | High performance |

---

### ⚙️ LLM Serving

| Tool | Notes |
|------|------|
| FastAPI (custom) | Simple, flexible |
| vLLM | High performance, OpenAI-compatible |
| Hugging Face TGI | Production-grade |
| Ollama | Local easy setup |

---

### 🧰 RAG Frameworks

| Tool | Notes |
|------|------|
| Spring AI | Native Java |
| LangChain | Popular |
| LlamaIndex | Strong ingestion |
| Haystack | Enterprise pipelines |

---

## 🧠 Mental Model

Think of your system like this:

- pgvector → finds relevant knowledge
- Spring AI → prepares context
- FastAPI → exposes model
- LLM → writes answer

---

## ⚠️ Key Insight

RAG is NOT:
> LLM + Database

RAG is:
> Carefully selecting context and guiding the model

---

## 🧭 Final Thought

Most system quality depends on:

- chunking strategy
- retrieval accuracy
- prompt design

Not the database. Not the model alone.

That’s where the real work lives.
