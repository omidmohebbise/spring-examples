package com.omidmohebbise.springjob.shedlock;

import com.mongodb.client.MongoClient;
import net.javacrumbs.shedlock.provider.mongo.MongoLockProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LockProviderConfig {

    private final MongoClient mongoClient;

    public LockProviderConfig(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Bean
    public MongoLockProvider lockProvider() {
        return new MongoLockProvider(mongoClient.getDatabase("shedlock"));
    }
}
