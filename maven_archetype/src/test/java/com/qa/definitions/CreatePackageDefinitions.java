package com.qa.definitions;

import com.qa.steps.CommonSteps;
import com.qa.steps.CreatePackageSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;

import static org.apache.http.HttpStatus.SC_OK;

public class CreatePackageDefinitions {

    private CommonSteps commonSteps = new CommonSteps();
    private CreatePackageSteps createPackageSteps = new CreatePackageSteps();

    @Given("^The service is up$")
    public void theServiceIsUp() throws IOException {
        commonSteps.setURI();
    }

    @And("^Get valid ids and save the response$")
    public void getValidIdsAndSaveTheResponse() throws IOException {
        commonSteps.setUserAndPass();
        createPackageSteps.getValidIds();
    }

    @When("^Hit \"([^\"]*)\" api to \"([^\"]*)\" data$")
    public void hitApiToData(String apiType, String functionality) throws IOException {
        switch (functionality) {
            case "create":
                commonSteps.readBodyParams(functionality);
                commonSteps.setHeaderParam("Content-Type", "application/json");
                createPackageSteps.hitRequest(apiType);
                commonSteps.saveLastResponseAsPrevious();
                break;
            case "view":
                createPackageSteps.hitRequest(apiType);
                break;
            case "update":
                commonSteps.readBodyParams(functionality);
                commonSteps.setHeaderParam("Content-Type", "application/json");
                createPackageSteps.hitRequest(apiType);
                break;
            case "delete":
                commonSteps.setPathParameters("id");
                createPackageSteps.hitRequest(apiType);
                break;
        }
    }

    @Then("^The api \"([^\"]*)\" is successful$")
    public void theApiIsSuccessful(String functionality) {
        switch (functionality) {
            case "create":
                commonSteps.theStatusCodeOfTheLastResponseIs(SC_OK);
                break;
            case "view":
                commonSteps.theStatusCodeOfTheLastResponseIs(SC_OK);
                createPackageSteps.validateIfIdMatches("id");
                break;
            case "update":
                commonSteps.theStatusCodeOfTheLastResponseIs(SC_OK);
                createPackageSteps.validateIfResponseIsUpdated("name");
                break;
            case "delete":
                commonSteps.theStatusCodeOfTheLastResponseIs(SC_OK);
                break;
        }
    }
}
