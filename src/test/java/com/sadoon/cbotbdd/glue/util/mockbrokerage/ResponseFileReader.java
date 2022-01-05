package com.sadoon.cbotbdd.glue.util.mockbrokerage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class ResponseFileReader {
    private final ObjectMapper mapper;

    private InputStream fileInputStream;

    public ResponseFileReader(ObjectMapper mapper, String brokerageKey) throws IOException {
        fileInputStream = ResponseFileReader.class.getResourceAsStream(System.getenv(brokerageKey));
        this.mapper = mapper;
    }

    public String getBody(String jsonKey) throws IOException {
        JsonNode node = mapper.readTree(fileInputStream);
        return node.get(jsonKey).toString();
    }

    public void close() throws IOException {
        fileInputStream.close();
    }

}