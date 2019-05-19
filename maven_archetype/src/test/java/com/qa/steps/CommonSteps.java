package com.qa.steps;

import com.qa.utils.PropertyReader;
import com.qa.utils.RequestBuilder;
import com.qa.utils.ResponsesWrapper;
import com.qa.verifiers.Verifier;
import org.json.JSONObject;
import org.junit.Assert;
import org.yecht.Data;

import java.io.IOException;
import java.util.Map;

public class CommonSteps {

    private RequestBuilder request = RequestBuilder.getInstance();
    private ResponsesWrapper response = ResponsesWrapper.getInstance();
    private Verifier verifier = new Verifier();
    private Map<String, Object> attributes;

    public void setURI() throws IOException {
        request.setURL(PropertyReader.readProperty("base_uri"));
    }

    public void theStatusCodeOfTheLastResponseIs(int code) {
        int responseCode = response.getLastStatusCode();
        Assert.assertEquals("Wrong status code in last response: " + responseCode, code, responseCode);
    }

    public void setHeaderParam(String key, String value) {
        request.setHeader(key, value);
    }

    public void readBodyParams(String functionality) throws IOException {
        String id= verifier.getLastId("id");
        String actualBody= null;
        JSONObject jsonBody= null;
        switch (functionality) {
            case "create":
                actualBody = PropertyReader.readProperty("create_package_body").replace("replaceByValidId", id);
                jsonBody = new JSONObject(actualBody);
                request.setJsonBody(jsonBody);
                break;
            case "update":
                String numId= String.valueOf(verifier.getNumId("id"));
                actualBody = PropertyReader.readProperty("update_package_body").replace("replaceByValidId", id).replace("111", numId);
                jsonBody = new JSONObject(actualBody);
                request.setJsonBody(jsonBody);
                break;
        }
    }

    public void saveLastResponseAsPrevious() {
        response.savePreviousResponse();
    }

    public void setUserAndPass() throws IOException {
        String username = PropertyReader.readProperty("username");
        String password = PropertyReader.readProperty("password");
        request.setUserAndPass(username, password);
    }

    public void setPathParameters(String id) {
        request.setPathParam(id, String.valueOf(verifier.getNumId("id")));
    }
}