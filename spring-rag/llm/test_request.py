import requests

payload = {
    "model": "ignored-by-demo",
    "messages": [
        {"role": "system", "content": "You are a concise assistant."},
        {"role": "user", "content": "What is retrieval augmented generation?"}
    ],
    "temperature": 0.2,
    "max_tokens": 120,
    "stream": False
}

r = requests.post("http://localhost:8000/v1/chat/completions", json=payload, timeout=120)
r.raise_for_status()
print(r.json()["choices"][0]["message"]["content"])
