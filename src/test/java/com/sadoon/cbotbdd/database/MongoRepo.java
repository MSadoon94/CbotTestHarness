package com.sadoon.cbotbdd.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoRepo implements Repository {
    private MongoCollection<Document> users;
    private MongoCollection<Document> krakenCards;

    public MongoRepo() {
        MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"));
        MongoDatabase database = mongoClient.getDatabase("test");
        users = database.getCollection("user");
        krakenCards = database.getCollection("krakenCard");
    }

    @Override
    public void deleteAllUsers() {
        users.deleteMany(new Document());
    }

    @Override
    public void deleteAllCards() {
        krakenCards.deleteMany(new Document());
    }
}
