package com.sadoon.cbotbdd.database;

import com.fasterxml.jackson.databind.JsonNode;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.sadoon.cbotbdd.util.JsonFileUtil;
import com.sadoon.cbotbdd.util.mockbrokerage.MockBrokerage;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;

public class MongoRepo {
    private MongoCollection<Document> users;
    private MongoCollection<Document> brokerages;
    private JsonFileUtil fileUtil;

    public MongoRepo(JsonFileUtil fileUtil) {
        this.fileUtil = fileUtil;
        MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"));
        MongoDatabase database = mongoClient.getDatabase("test");
        users = database.getCollection("user");
        brokerages = database.getCollection("brokerage");
    }

    public void deleteAllUsers() {
        users.deleteMany(new Document());
    }

    public InsertOneResult addUser(String userDetails) {
        Document user = Document.parse(userDetails);

        return users.insertOne(user);
    }

    public UpdateResult updateUser(String username, String property, Object value) {
        Document userQuery = new Document().append("username", username);
        Bson updateValue = Updates.set(property, value);
        return users.updateOne(userQuery, updateValue);
    }

    public UpdateResult replaceUser(String username, Document user){
        return users.replaceOne(eq("username", username), user);
    }

    public UpdateResult setMockBrokerageUrl(MockBrokerage mockBrokerage) {
        Document mockBrokerageQuery = new Document().append("name", mockBrokerage.getBrokerageName());
        Bson updateUrl = Updates.set("url", mockBrokerage.getBrokerageUrl());
        return brokerages.updateOne(mockBrokerageQuery, updateUrl);
    }

    public Document getUserAsDocument(String user){
        return Objects.requireNonNull(
                users.find(eq("username", user)).first());
    }
    public JsonNode getUserAsNode(String user) {
        return fileUtil.bodyToNode(
                Objects.requireNonNull(
                        users.find(eq("username", user)).first()).toJson());
    }
}
