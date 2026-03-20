#!/usr/bin/env bash
set -euo pipefail

export HF_MODEL_ID="${HF_MODEL_ID:-microsoft/Phi-3-mini-4k-instruct}"
export HOST="${HOST:-0.0.0.0}"
export PORT="${PORT:-8000}"

python3 app.py