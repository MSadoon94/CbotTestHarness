package com.sadoon.cbotbdd.glue.util.mockbrokerage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.io.InputStream;

import static com.sadoon.cbotbdd.glue.util.TestListener.LOGGER;
import static com.sadoon.cbotbdd.glue.util.TestListener.MAPPER;

public class JsonFileUtil {
    private InputStream fileInputStream;

    public void setFileName(String envKey) {
        fileInputStream = JsonFileUtil.class.getResourceAsStream(System.getenv(envKey));
    }

    public String getBody(String jsonKey) {
        JsonNode node = MAPPER.nullNode();
        try {
            node = MAPPER.readTree(fileInputStream);
        } catch (IOException e) {
            LOGGER.error(String.format("Error while reading: %1s caused by: %2s", e.getMessage(), e.getCause()));
        }
        return node.get(jsonKey).toString();
    }

    public JsonNode bodyToNode(String body) {
        JsonNode node = MAPPER.nullNode();
        try {
            node = MAPPER.readTree(body);
        } catch (JsonProcessingException e) {
            LOGGER.error((String.format("Json error caused at location: %1s  with message: %2s",
                    e.getLocation(), e.getMessage())));
        }
        return node;
    }

    public void close() throws IOException {
        fileInputStream.close();
    }

}