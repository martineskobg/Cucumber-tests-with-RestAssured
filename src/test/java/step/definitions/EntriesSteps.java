package step.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class EntriesSteps {
    private int expectedEntriesCount;
    private int actualEntriesCount;
    private String entryParameter;
    private List<Map<Object, Object>> entriesList = new ArrayList<>();
    private int getExpectedEntriesHttpsCount;
    private int actualEntriesHttpsCount;


    @Given("Api base url for entries {string}")
    public void apiUrlBaseUrlForEntries(String url) {
        System.out.println("Set base url = " + url);
        RestAssured.baseURI = url;
    }

    @Given("Expected count of all entries is {int}")
    public void expected_count_of_all_entries_is(Integer count) {
        System.out.println("Expected count = " + count);
        expectedEntriesCount = count;
    }

    @When("Get the actual count")
    public void get_the_actual_count() {
        actualEntriesCount = given()
                .get()
                .jsonPath()
                .getInt("count");
        System.out.println("Get actual count: " + actualEntriesCount);
    }

    @Then("Verify that expected count is equal to actual")
    public void verify_that_expected_count_is_equal_to_actual() {
        System.out.println("Verify that expected count is equal to actual");
        Assertions.assertEquals(expectedEntriesCount, actualEntriesCount, "Expected count is not equal to actual count of entries!");
    }

    @Given("Entry parameter {}")
    public void entryParameterParameter(String param) {
        System.out.println("Get parameter: " + param);
        entryParameter = param;
    }

    @When("Get all entries")
    public void getAllEntriesAsAList() {
        System.out.println("Get all Entries");
        entriesList = given().get().jsonPath().getList("entries");
    }

    @Then("Verify that the entry contains the given parameter")
    public void verifyThatTheEntryContainsTheGivenParameter() {
        System.out.println("Verify that the entry contains the given parameter");
        boolean hasParameter;
        for (Map<Object, Object> entriesMap : entriesList) {
            hasParameter = entriesMap.containsKey(entryParameter);
            Assertions.assertTrue(hasParameter, "The entry doesn't have parameter = " + entryParameter);
        }
    }

    @Given("Count of entries with parameter HTTPS false is {int}")
    public void countOfEntriesWithParameterHTTPSFalseIs(int count) {
        System.out.println("Get expected Count of entries with parameter HTTPS false");
        getExpectedEntriesHttpsCount = count;
    }

    @When("Get all entries with parameter HTTPS equal to false")
    public void getAllEntriesWithParameterHTTPSEqualToFalse() {
        System.out.println("Get all entries with parameter HTTPS equal to false");
        actualEntriesHttpsCount = given()
                .when().queryParam("https", "false")
                .get()
                .jsonPath()
                .getInt("count");
    }

    @Then("Verify expected and actual count")
    public void verifyExpectedAndActualCount() {
        System.out.println("Verify expected and actual count");
        Assertions.assertEquals(getExpectedEntriesHttpsCount, actualEntriesHttpsCount, "The expected count is not equal to "
                + actualEntriesHttpsCount);
    }
}
