package com.sadoon.cbotbdd.util;

import com.sadoon.cbotbdd.database.MongoRepo;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.bson.Document;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DatabaseInitializer {
    private final MongoRepo repo;
    private JsonFileUtil fileUtil;
    private final String USERNAME = "TestUser";

    public DatabaseInitializer() {
        fileUtil = new JsonFileUtil();
        fileUtil.setFileName("MOCK_DATA_JSON");
        repo = new MongoRepo(fileUtil);
    }

    public MongoRepo getRepo() {
        return repo;
    }

    public JsonFileUtil getFileUtil() {
        return fileUtil;
    }

    @Before(value = "not (@sign-up)", order = 0)
    public void setUpWithUserSaved() {
        repo.deleteAllUsers();
        assertThat(repo.addUser(
                fileUtil.getBody("User")).wasAcknowledged(), is(true));

    }

    @Before("@cbot-activated")
    public void setCbotStatusAsActivated() {
        Document user = repo.getUserAsDocument(USERNAME);
        user.append("cbotStatus",
                Map.of("isActive", true,
                        "activeStrategies", List.of("MockStrategy"))
        );
        repo.replaceUser(USERNAME, user);
    }

    @After
    public void closeReader() {
        try {
            fileUtil.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}