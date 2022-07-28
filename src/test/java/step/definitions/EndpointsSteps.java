package step.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

public class EndpointsSteps {
    private int actualStatusCode;

    @Given("Api url {string}")
    public void api_url(String apiBaseUrl) {
        System.out.println("Set baseURL = " + apiBaseUrl);
        RestAssured.baseURI = apiBaseUrl;
    }

    @Given("endpoint is: {}")
    public void endpointIsEndpoint(String endpoint) {
        RestAssured.baseURI += endpoint;
        System.out.println("Set endpoint: " + endpoint);
    }

    @When("get Response Status")
    public void get_response_status() {
        actualStatusCode = given()
                .when()
                .get()
                .statusCode();
        System.out.println("Get Response");
    }

    @Then("Verify Status code is {int}")
    public void verify_status_code_is(Integer expectedStatusCode) {
        Assertions.assertEquals(expectedStatusCode, actualStatusCode,
                String.format("The expected status %d is not equal to actual status %d!",
                        expectedStatusCode, actualStatusCode));
        System.out.println("Verify Status code: " + expectedStatusCode);
    }
}
