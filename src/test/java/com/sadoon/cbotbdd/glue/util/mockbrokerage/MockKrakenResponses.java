package com.sadoon.cbotbdd.glue.util.mockbrokerage;

import okhttp3.mockwebserver.MockResponse;


import java.io.IOException;

public class MockKrakenResponses implements MockBrokerageResponses{

    private final ResponseFileReader fileReader;

    public MockKrakenResponses(ResponseFileReader fileReader) {
        this.fileReader = fileReader;
    }

    public MockResponse balance(boolean isFailResponse) throws IOException {
        if(isFailResponse){
            return new MockResponse()
                    .setResponseCode(400);
        } else {
            return new MockResponse()
                    .setResponseCode(200)
                    .setBody(fileReader.getBody("balance"));
        }
    }


}