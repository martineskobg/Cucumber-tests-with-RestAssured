package step.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

public class EndpointsSteps {
    private Response response;

    @Given("I have API url {string}")
    public void api_base_url(String apiBaseUrl) {
        System.out.println("Set baseURL = " + apiBaseUrl);
        RestAssured.baseURI = apiBaseUrl;
    }


    @When("I call {} endpoint")
    public void get_response_status(String endpoint) {
        System.out.println("Open " + endpoint + " endpoint");
        RestAssured.baseURI += endpoint;
        response  = RestAssured.given().get();
    }

    @Then("I verify that the status code is {int}")
    public void verify_status_code_is(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        Assertions.assertEquals(expectedStatusCode, actualStatusCode, String.format("The expected status %d is not equal to actual status %d!",
                        expectedStatusCode, actualStatusCode));
        System.out.println("Verify Status code: " + expectedStatusCode);
    }
}
