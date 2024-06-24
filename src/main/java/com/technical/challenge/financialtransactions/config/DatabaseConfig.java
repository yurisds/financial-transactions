package com.technical.challenge.financialtransactions.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoManagedTypes;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.technical.challenge.financialtransactions.repository")
public class DatabaseConfig extends AbstractMongoClientConfiguration {

    @Value("${db.config.database}")
    private String databaseName;

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create(databaseUrl);
    }

}
