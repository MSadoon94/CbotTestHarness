package com.sadoon.cbotbdd.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.sadoon.cbotbdd.glue.util.mockbrokerage.MockBrokerage;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Map;

public class MongoRepo {
    private MongoCollection<Document> users;
    private MongoCollection<Document> brokerages;

    public MongoRepo() {
        MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"));
        MongoDatabase database = mongoClient.getDatabase("test");
        users = database.getCollection("user");
        brokerages = database.getCollection("brokerage");
    }

    public void deleteAllUsers() {
        users.deleteMany(new Document());
    }

    public UpdateResult updateUser(String username, String property, Object value){
        Document userQuery = new Document().append("username", username);
        Bson updateValue = Updates.set(property, value);
        return users.updateOne(userQuery, updateValue);
    }
    public UpdateResult setMockBrokerageUrl(MockBrokerage mockBrokerage){
        Document mockBrokerageQuery = new Document().append("name", mockBrokerage.getBrokerageName());
        Bson updateUrl = Updates.set("url", mockBrokerage.getBrokerageUrl());
        return brokerages.updateOne(mockBrokerageQuery, updateUrl);
    }

    public UpdateResult setStrategies(String username, Map<String, Object> strategies){
        Document userFilter= new Document().append("username", username);
        Bson updateStrategies = Updates.set("strategies", strategies);
       return users.updateOne(userFilter, updateStrategies);
    }
}
