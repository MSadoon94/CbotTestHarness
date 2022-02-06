package com.sadoon.cbotbdd.util.mockbrokerage;

import com.sadoon.cbotbdd.database.MongoRepo;
import com.sadoon.cbotbdd.util.JsonFileUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.io.IOException;

public class MockBrokerageFactory {

    private JsonFileUtil fileReader;
    private MockBrokerage brokerage;
    private final MongoRepo repo;
    private String brokerageName;
    private ResponseHandler responseHandler;
    private boolean isFailResponse = false;

    public MockBrokerageFactory(MongoRepo repo){
        this.repo = repo;
    }

    @Before(value = "@mock-kraken", order = 1)
    public void setUpMockKraken() {
        brokerageName = "mockkraken";
        setFileReader();
        responseHandler = new ResponseHandler(new MockKrakenResponses(fileReader));
    }

    @Before(value = "@fail-response", order = 2)
    public void setUpBrokerageFailState(){
        isFailResponse = true;
    }

    @Before("@mock-brokerage or @fail-response")
    public void setUpMockBrokerage(){
        brokerage = new MockBrokerage(brokerageName, responseHandler.dispatcher(isFailResponse));
        repo.setMockBrokerageUrl(brokerage);
    }

    private void setFileReader(){
        fileReader = new JsonFileUtil();
        fileReader.setFileName(brokerageName);
    }

    @After("@mock-kraken")
    public void shutDownBrokerage() {
        try {
            fileReader.close();
            brokerage.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
