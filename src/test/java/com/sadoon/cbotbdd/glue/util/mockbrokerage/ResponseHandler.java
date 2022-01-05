package com.sadoon.cbotbdd.glue.util.mockbrokerage;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;

import java.io.IOException;

public class ResponseHandler {

    private MockBrokerageResponses responses;

    public ResponseHandler(MockBrokerageResponses responses){
        this.responses = responses;
    }

    public Dispatcher dispatcher(boolean isFailResponse) {
        return new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                try {
                    switch (request.getPath()) {
                        case "/mockBalance":
                            return responses.balance(isFailResponse);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return new MockResponse().setResponseCode(404);
            }
        };
    }

}