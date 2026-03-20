# Life Principles RAG Project

## Overview
This project is a Retrieval-Augmented Generation (RAG) application built with Spring Boot.

It provides life guidance tailored to different age groups by:
- storing age-based life principles
- retrieving relevant principles based on user age
- generating grounded answers using an LLM

---

## What this project does

Example question:
How should I think about money?

The system:
- filters knowledge by age group
- retrieves relevant principles
- generates an answer using an LLM with context

---

## Tech Stack

| Tool | Purpose |
|------|--------|
| Spring Boot | REST API & application framework |
| Spring AI | RAG orchestration (embeddings, retrieval, prompt building) |
| OpenAI | Embeddings + LLM |
| PostgreSQL | Data storage |
| pgvector | Vector similarity search |
| Docker | Database setup |

---

## Architecture

User → Controller → Spring AI → pgvector → Spring AI → LLM → Response

---

## How it works

1. Life principles are ingested
2. Each principle is converted into embeddings
3. Stored in PostgreSQL using pgvector
4. User sends question + age
5. Question is embedded
6. pgvector retrieves similar documents
7. Spring AI builds prompt
8. LLM generates answer

---

## Responsibilities

| Component | Responsibility |
|----------|---------------|
| Spring AI | Coordinates pipeline |
| Embedding Model | Text → vector |
| pgvector | Similarity search |
| Advisor | Prompt building |
| ChatClient | Calls LLM |
| LLM | Generates answer |

---

## Project Structure

src/main/java/com/example/rag

- IngestionConfig.java
- LifePrinciplesController.java
- LifePrinciplesService.java
- AgeGroupService.java

---

## Data Example

{
  "content": "Save money and avoid lifestyle inflation.",
  "metadata": {
    "ageGroup": "25-30",
    "category": "life-principles"
  }
}

---

## Run the project

1. Start database:
docker compose up -d

2. Enable extension:
CREATE EXTENSION IF NOT EXISTS vector;

3. Run app:
./gradlew bootRun

---

## Example request

curl "http://localhost:8080/life-principles?age=27&question=How should I think about money?"

---

## Notes

- pgvector only handles similarity search
- LLM generates answers
- Quality depends on retrieval + prompt

---

## Alternatives

### Vector DB
- pgvector
- Pinecone
- Weaviate
- Qdrant
- Milvus

### Frameworks
- Spring AI
- LangChain
- LlamaIndex
- Haystack

---

## Key idea

RAG = selecting the right context before asking the LLM.
