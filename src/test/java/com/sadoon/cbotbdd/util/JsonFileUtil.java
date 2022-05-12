package com.sadoon.cbotbdd.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.io.InputStream;

import static com.sadoon.cbotbdd.util.TestListener.LOGGER;
import static com.sadoon.cbotbdd.util.TestListener.MAPPER;

public class JsonFileUtil {
    private InputStream fileInputStream;
    private JsonNode baseNode;

    public void setFileName(String envKey) {
        fileInputStream = JsonFileUtil.class.getResourceAsStream(System.getenv(envKey));
        try {
            baseNode = MAPPER.readTree(fileInputStream);
        } catch (IOException e) {
            LOGGER.error(String.format("Error while reading: %1s caused by: %2s", e.getMessage(), e.getCause()));
        }
    }

    public String getBody(String jsonKey) {
        return baseNode.get(jsonKey).toString();
    }

    public JsonNode bodyToNode(String body) {
        JsonNode node = MAPPER.nullNode();
        try {
            node = MAPPER.readTree(body);
        } catch (JsonProcessingException e) {
            LOGGER.error(String.format("Error while reading: %1s caused by: %2s", e.getMessage(), e.getCause()));
        }
        return node;
    }

    public void close() throws IOException {
        fileInputStream.close();
    }

}