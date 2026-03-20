package com.omidmohebbise.springboot3;


import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IngestionConfig {

    @Bean
    CommandLineRunner ingest(VectorStore vectorStore) {
        return args -> {
            List<Document> docs = List.of(
                    new Document("Spring AI supports retrieval augmented generation."),
                    new Document("PGvector stores embeddings in PostgreSQL."),
                    new Document("RAG helps answer questions using your own documents.")
            );
            vectorStore.add(docs);
        };
    }
}