package com.sadoon.cbotbdd.glue.util.mockbrokerage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class ResponseFileReader {
    private final ObjectMapper mapper;

    private InputStream fileInputStream;

    public ResponseFileReader(ObjectMapper mapper, String envKey) throws IOException {
        fileInputStream = ResponseFileReader.class.getResourceAsStream(System.getenv(envKey));
        this.mapper = mapper;
    }

    public String getBody(String jsonKey) throws IOException {
        JsonNode node = mapper.readTree(fileInputStream);
        return node.get(jsonKey).toString();
    }
    public Map<?, ?> getBodyAsMap() throws IOException {
        return mapper.readValue(fileInputStream, Map.class);
    }

    public void close() throws IOException {
        fileInputStream.close();
    }

}