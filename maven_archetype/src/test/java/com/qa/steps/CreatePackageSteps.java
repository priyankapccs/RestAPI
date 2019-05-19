package com.qa.steps;

import com.qa.utils.RequestBuilder;
import com.qa.verifiers.Verifier;

import static com.qa.config.Constants.*;

public class CreatePackageSteps {

    private RequestBuilder request = RequestBuilder.getInstance();
    private Verifier verifier = new Verifier();

    public void hitRequest(String apiType) {
        switch (apiType) {
            case "POST":
                request.post(CREATE_PACKAGE);
                break;
            case "GET":
                request.get(CREATE_PACKAGE);
                break;
            case "PUT":
                request.put(CREATE_PACKAGE);
                break;
            case "DELETE":
                request.delete(DELETE_PACKAGE);
                break;
        }
    }

    public void getValidIds() {
        request.get(GET_ID);
    }

    public void validateIfIdMatches(String id) {
        verifier.validateIfIdMatchesForPostAndGet(id);
    }

    public void validateIfResponseIsUpdated(String value) {
        verifier.validateIfresponseIsUpdated(value);
    }
}
