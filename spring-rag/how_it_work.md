# How RAG Works (Practical Mental Model)

## The Simple Idea

RAG (Retrieval-Augmented Generation) is not magic.  
It is just a way of giving the LLM **extra context before it answers**.

---

## Without RAG

```
User → LLM → Answer
```

- The model answers based only on its training
- No access to your private or recent data

---

## With RAG

```
User → Retrieve relevant data → LLM → Answer
```

- The model gets additional context
- The answer is influenced by your data

---

## What “Context” Really Means

You are not giving the whole database.

You are giving:
- A few selected text chunks
- Chosen based on semantic similarity

Think of it as:

> “Only the most relevant pieces of meaning are shown to the model.”

---

## What the LLM Actually Sees

The model does NOT see:
- vectors
- databases
- embeddings

It only sees a prompt like this:

```
You are a helpful assistant.

Use the following context to answer the question.
If the answer is not in the context, say you don't know.

Context:
--------
PGvector stores embeddings in PostgreSQL.
RAG helps answer questions using your own data.
--------

Question:
How do I store embeddings?

Answer:
```

---

## The Real Flow

```
Text → Chunking → Embeddings → Stored in DB

User Question → Embedding → Similarity Search → Top K Chunks

→ Build Prompt → Send to LLM → Answer
```

---

## Important Shift in Thinking

Without RAG:
> “What does the model know?”

With RAG:
> “What did we show the model right now?”

---

## What RAG Actually Changes

RAG does NOT make the model smarter.

It makes the model:
- Less blind
- More grounded
- More controlled

---

## Mental Model

Think of it like an open-book exam:

- LLM = student
- Your data = book
- RAG = which pages you open

The answer depends on the pages you choose.

---

## Where Things Go Wrong

Most issues are not technical — they are about quality:

### 1. Bad retrieval
- Wrong chunks selected
- Irrelevant context

### 2. Too much context
- Model gets confused
- Signal gets diluted

### 3. Weak prompt
- Model ignores context
- Hallucinates anyway

---

## The Two Levers You Control

1. **Retrieval (what you show)**
2. **Prompt (how you show it)**

Everything else is secondary.

---

## Final Thought

RAG is not:

> “LLM + database”

It is:

> “Carefully construct a message that makes the LLM behave using selected context”

And most of the real problems live in that construction step.
