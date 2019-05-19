package com.qa.verifiers;

import com.qa.pathValidator.PathValidator;
import com.qa.utils.ResponsesWrapper;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;

import java.util.List;

public class Verifier {

    public Verifier() {
        response = ResponsesWrapper.getInstance();
    }

    /**
     * Contains the last response of the micro-service
     */
    private ResponsesWrapper response;

    public String getLastId(String value) {
        JsonPath actualJsonPath = response.getLastJsonPath();
        List<Object> id = actualJsonPath.getList(value);
        return (String) id.get(0);
    }

    public void validateIfIdMatchesForPostAndGet(String id) {
        JsonPath actualJsonPath = response.getLastJsonPath();
        JsonPath expectedJsonPath = response.getPreviousJsonPath();
        Assert.assertTrue(actualJsonPath.getList(PathValidator.getAttribute(id)).toString().contains(expectedJsonPath.get(PathValidator.getAttribute(id)).toString()));
    }

    public void validateIfresponseIsUpdated(String value) {
        Assert.assertTrue(response.getLastJsonPath().get(PathValidator.getAttribute(value)).toString().contains("updated"));
    }

    public int getNumId(String id) {
        JsonPath actualJsonPath = response.getPreviousJsonPath();
        int numId = actualJsonPath.get(id);
        return numId;
    }
}
