package step.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

public class RandomSteps {
    private int expectedRandomCount;
    private Response responseRandom;
    private int actualRandomCount;
    private Boolean httpsBoolean;
    private int expectedParametersCount;
    private int actualParametersCount;
    private Map<Object, Object> randomMap;
    private String httpsParameter;

    @Given("Api base url for random {string}")
    public void api_base_url_for_random(String apiBaseUrl) {
        RestAssured.baseURI = apiBaseUrl;
        System.out.println("Set baseURL = " + apiBaseUrl);
    }

    @Given("Expected value of count {int}")
    public void expected_value_of_count(int count) {
        expectedRandomCount = count;
        System.out.println("Expected count: " + count);
    }

    @When("Get the actual random count")
    public void get_the_actual_random_count() {
        responseRandom = RestAssured.given().get();
        actualRandomCount = responseRandom.jsonPath().getInt("count");
        System.out.println("Get the actual random count: " + actualRandomCount);
    }

    @Then("Verify the count")
    public void verify_the_count() {
        System.out.println("Verify the count");
        Assertions.assertEquals(expectedRandomCount, actualRandomCount, "Expected count is not equal to actual count of random endpoint!");
    }

    @Given("Expected count of the random endpoint parameters is {int}")
    public void expected_count_of_the_random_endpoint_parameters_is(int parametersCount) {
        expectedParametersCount = parametersCount;
        System.out.println("Expected count of the random endpoint parameters " + expectedParametersCount);

    }

    @When("Take the actual count of the random parameters")
    public void take_the_actual_count_of_the_random_parameters() {
        responseRandom = RestAssured.given().get();
        randomMap = responseRandom.jsonPath().getMap("entries[0]");
        actualParametersCount = randomMap.size();
        System.out.println("Actual count of the random endpoint parameters " + expectedParametersCount);
    }

    @Then("Verify that the expected and actual count are equal")
    public void verify_that_the_expected_and_actual_count_are_equal() {
        Assertions.assertEquals(expectedParametersCount, actualParametersCount,
                "Expected count of parameters is not equal to Actual count!");
        System.out.println("Checking the expected and actual count!");
    }

    @Given("Parameter {string}")
    public void parameter_https_is_true(String httpsParam) {
        httpsParameter = httpsParam;
    }

    @When("Get the value of HTTPS parameter")
    public void get_the_value_of_https_parameter() {
        responseRandom = RestAssured.given().get();
        httpsBoolean = responseRandom.jsonPath().getBoolean("entries[0]." + httpsParameter);
        System.out.println("Get HTTPS parameter value: " + httpsBoolean);
    }

    @Then("Verify that HTTPS has value {booleanValue}")
    public void verify_that_https_is_true(Boolean expectedValue) {
        Assertions.assertSame(expectedValue, httpsBoolean, "HTTPS parameter is not " + httpsBoolean);
        System.out.println("Check if HTTPS parameter has value: " + expectedValue);
    }

}
