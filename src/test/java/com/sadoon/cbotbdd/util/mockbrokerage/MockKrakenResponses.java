package com.sadoon.cbotbdd.util.mockbrokerage;

import com.sadoon.cbotbdd.util.JsonFileUtil;
import okhttp3.mockwebserver.MockResponse;

public class MockKrakenResponses implements MockBrokerageResponses{

    private final JsonFileUtil fileReader;

    public MockKrakenResponses(JsonFileUtil fileReader) {
        this.fileReader = fileReader;
    }

    public MockResponse balance(boolean isFailResponse) {
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