# RAG Project README

## Overview
This project demonstrates a Retrieval-Augmented Generation (RAG) system using Spring Boot, Spring AI, PostgreSQL with pgvector, and OpenAI models.

The goal is to:
- Store life principles by age group
- Retrieve relevant principles based on user age
- Generate answers grounded in those principles

---

## Architecture Overview

```
User → Controller → Spring AI → pgvector → Spring AI → LLM → Response
```

---

## Components and Responsibilities

| Component | Responsibility |
|----------|---------------|
| Spring Boot | Application framework, REST API |
| Spring AI | Orchestrates embeddings, retrieval, prompt building |
| OpenAI Embedding Model | Converts text into vectors |
| PostgreSQL | Stores data |
| pgvector | Performs vector similarity search |
| ChatClient (Spring AI) | Communicates with LLM |
| LLM (OpenAI) | Generates final answer |

---

## Detailed Responsibilities

### Spring AI
- Generates embeddings
- Executes similarity search queries
- Applies metadata filters (e.g., age group)
- Builds prompt with retrieved context
- Sends request to LLM

### pgvector
- Stores vector embeddings
- Computes similarity between vectors
- Returns closest matches (Top-K)
- Provides indexing for performance

### LLM
- Understands natural language
- Uses provided context to generate answers
- Does NOT access database directly

---

## Data Flow

1. Documents are ingested and converted to embeddings
2. Embeddings are stored in PostgreSQL with pgvector
3. User submits a question with age
4. Query is converted into embedding
5. pgvector retrieves closest matching documents
6. Spring AI builds prompt with retrieved context
7. LLM generates answer

---

## Key Insight

RAG is not:
> LLM + Database

RAG is:
> Carefully selecting context and constructing a prompt

---

## Similar Products / Alternatives

### Vector Databases

| Tool | Type | Notes |
|------|------|------|
| pgvector | PostgreSQL extension | Simple, embedded in Postgres |
| Pinecone | Managed vector DB | Fully managed, scalable |
| Weaviate | Vector DB | Supports hybrid search |
| Milvus | Open-source vector DB | High performance |
| Qdrant | Vector DB | Good filtering support |

---

### LLM Providers

| Tool | Notes |
|------|------|
| OpenAI | Widely used, strong models |
| Anthropic | Claude models, strong reasoning |
| Google Gemini | Integrated ecosystem |
| Mistral | Open-weight models |

---

### Orchestration / RAG Frameworks

| Tool | Notes |
|------|------|
| Spring AI | Native for Spring ecosystem |
| LangChain | Popular Python/JS framework |
| LlamaIndex | Strong for data ingestion |
| Haystack | Enterprise-ready pipelines |

---

## Final Thoughts

- pgvector handles similarity, not intelligence
- Spring AI orchestrates retrieval and prompting
- LLM generates the answer

Most issues in RAG systems come from:
- Poor chunking
- Weak retrieval
- Bad prompt design

Focus there for improvement.
