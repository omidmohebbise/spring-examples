docker exec -it rag-postgres psql -U postgres -d ragdb
CREATE EXTENSION IF NOT EXISTS vector;
SELECT * FROM pg_extension;
