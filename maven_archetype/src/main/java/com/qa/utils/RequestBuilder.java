package com.qa.utils;

import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.*;
import org.json.JSONObject;

import static net.serenitybdd.rest.SerenityRest.rest;

public class RequestBuilder {

    private RequestBuilder() {
        resetSpecification();
        response = ResponsesWrapper.getInstance();
    }

    private ResponsesWrapper response;
    private RequestSpecification rs;
    private static volatile RequestBuilder thisObject;

    public static synchronized RequestBuilder getInstance() {
        if (thisObject == null) thisObject = new RequestBuilder();
        return thisObject;
    }

    public void setURL(String baseUri) {
        rs.baseUri(baseUri);
    }

    public RequestBuilder setJsonBody(JSONObject body) {
        String bodyParam = body.toString().replace("\\", "");
        return setBody(bodyParam);
    }

    private RequestBuilder setBody(String body) {
        rs.body(body);
        return this;
    }

    public void post(String endpoint) {
        response.setLastResponse(rs.post(endpoint));
    }

    public void get(String endpoint) {
        response.setLastResponse(rs.get(endpoint));
    }

    public RequestBuilder resetSpecification() {
        rs = rest().config(RestAssuredConfig.config().encoderConfig(EncoderConfig.encoderConfig().defaultContentCharset("UTF-8")));
        return this;
    }

    public RequestBuilder setHeader(String fieldName, String value) {
        rs.header(fieldName, value);
        return this;
    }

    public void setUserAndPass(String username, String password) {
        rs.auth().basic(username, password);
    }

    public void put(String endpoint) {
        response.setLastResponse(rs.put(endpoint));
    }

    public void delete(String endpoint) {
        response.setLastResponse(rs.delete(endpoint));
    }

    public RequestBuilder setPathParam(String fieldName, String value) {
        rs.pathParam(fieldName, value);
        return this;
    }
}
