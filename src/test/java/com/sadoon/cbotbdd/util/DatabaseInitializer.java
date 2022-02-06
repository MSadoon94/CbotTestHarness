package com.sadoon.cbotbdd.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.sadoon.cbotbdd.database.MongoRepo;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.bson.Document;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static com.sadoon.cbotbdd.util.TestListener.MAPPER;

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

    @Before("@load-card")
    public void setCardPassword(){
        String password = new MockPasswordGenerator()
                        .encryptCardPassword("MockCard", "MockCardPassword", USERNAME);

        JsonNode user = repo.getUserAsNode(USERNAME);
        Map card = MAPPER.convertValue(user.get("cards").get("MockCard"), Map.class);
        card.put("password", password);
        Document userDoc = repo.getUserAsDocument(USERNAME);
        userDoc.append("cards",
                Map.of("MockCard", card));

        repo.replaceUser(USERNAME, userDoc);
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