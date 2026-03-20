# pgvector vs PostgreSQL (RAG Guide)

## Overview
This document explains the difference between a standard PostgreSQL setup and using pgvector for Retrieval-Augmented Generation (RAG).

---

## Normal PostgreSQL
PostgreSQL is a relational database designed for structured data:
- Tables, rows, indexes
- Supports numbers, text, JSON

### Limitation
PostgreSQL does NOT understand vector similarity.

Example:
```
[0.12, -0.98, 0.44, ...]
```

This is treated as plain data, not something meaningful for semantic search.

---

## pgvector (PostgreSQL + Vector Extension)

pgvector extends PostgreSQL with vector capabilities.

### 1. Vector Data Type
```
embedding vector(1536)
```

Allows storing embeddings natively.

---

### 2. Similarity Search
```
SELECT *
FROM documents
ORDER BY embedding <-> '[0.1, 0.2, ...]'
LIMIT 5;
```

- `<->` = distance operator
- Enables semantic search

---

### 3. Vector Indexing
```
CREATE INDEX ON documents USING ivfflat (embedding vector_cosine_ops);
```

Enables fast similarity search at scale.

---

## Comparison

| Feature | PostgreSQL | pgvector |
|--------|-----------|---------|
| Standard DB features | Yes | Yes |
| Embedding storage | No | Yes |
| Similarity search | No | Yes |
| Indexed vector search | No | Yes |
| RAG-ready | No | Yes |

---

## Installing pgvector

Using Docker image:
```
image: pgvector/pgvector:pg16
```

Enable extension:
```
CREATE EXTENSION IF NOT EXISTS vector;
```

---

## How It Works in RAG (Spring AI)

1. Documents are converted into embeddings
2. Stored in PostgreSQL with pgvector
3. Query is converted into embedding
4. Similar documents are retrieved
5. LLM generates answer using retrieved context

---

## Key Insight

Without pgvector:
- Retrieval is inefficient or impossible

With pgvector:
- Retrieval is fast and scalable

---

## Tradeoffs

### Pros
- Simple architecture
- Works inside PostgreSQL
- Easy to integrate with Spring AI

### Cons
- Less advanced than dedicated vector DBs (Pinecone, Weaviate)
- Limited advanced retrieval features

---

## When to Use pgvector

Use pgvector when:
- You already use PostgreSQL
- You want a simple RAG setup
- You prefer fewer moving parts

---

## Final Thought

pgvector turns PostgreSQL from a traditional database into a semantic search engine.

The real challenge is not the database —
it's how good your retrieval is.
