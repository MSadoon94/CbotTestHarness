package com.sadoon.cbotbdd.glue.util.mockbrokerage;

import okhttp3.mockwebserver.MockResponse;

import java.io.IOException;

public interface MockBrokerageResponses {

    MockResponse balance(boolean isFailResponse) throws IOException;
}
