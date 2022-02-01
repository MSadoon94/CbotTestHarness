package com.sadoon.cbotbdd.glue.util.mockbrokerage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sadoon.cbotbdd.database.MongoRepo;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.io.IOException;

public class MockBrokerageFactory {

    private ResponseFileReader fileReader;
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
        try {
            fileReader = new ResponseFileReader(new ObjectMapper(), brokerageName);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
