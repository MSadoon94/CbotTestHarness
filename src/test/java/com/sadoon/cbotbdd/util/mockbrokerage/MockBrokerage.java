package com.sadoon.cbotbdd.util.mockbrokerage;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;

import java.io.IOException;

public class MockBrokerage {
    private MockWebServer brokerage;
    private String brokerageName;


    public MockBrokerage(String brokerageName, Dispatcher dispatcher) {
        this.brokerageName = brokerageName;
        brokerage = new MockWebServer();
        brokerage.setDispatcher(dispatcher);
        try {
            brokerage.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBrokerageUrl() {
        return brokerage.url("/").toString();
    }

    public String getBrokerageName() {
        return brokerageName;
    }

    public void shutdown() throws IOException {
        brokerage.shutdown();
    }


}
