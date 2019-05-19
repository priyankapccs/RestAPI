package com.qa.utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;

public class ResponsesWrapper {

    public ResponsesWrapper() {
    }

    private Response previousResponse;
    private Response lastResponse;
    private static volatile ResponsesWrapper thisObject;

    public synchronized void setLastResponse(Response resp) {
        lastResponse = resp;
    }

    public static synchronized ResponsesWrapper getInstance() {
        if (thisObject == null) thisObject = new ResponsesWrapper();
        return thisObject;
    }

    public int getLastStatusCode() {
        return lastResponse.getStatusCode();
    }

    public synchronized void savePreviousResponse() {
        previousResponse = lastResponse;
    }

    public JsonPath getPreviousJsonPath() {
        return this.getPreviousResponse().jsonPath();
    }

    public synchronized Response getPreviousResponse() {
        return previousResponse;
    }

    public JsonPath getLastJsonPath() {
        return this.getLastResponse().jsonPath();
    }

    public synchronized Response getLastResponse() {
        return lastResponse;
    }
}
