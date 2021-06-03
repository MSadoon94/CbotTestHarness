package com.sadoon.cbotbdd.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class MongoRepo implements Repository {
    private final MongoClient client = MongoClients.create(
                    "mongodb+srv://" +
                    "TestUser:3fjiWn3jlAua8Dww" +
                    "@cbotprofiles.5acgt.mongodb.net" +
                    "/test?retryWrites=true&w=majority");
    private final MongoDatabase database = client.getDatabase("test");
    private final MongoCollection<Document> users = database.getCollection("users");


    @Override
    public void deleteUser(String user) {
        users.deleteOne(Filters.eq("username", user));
    }
}
