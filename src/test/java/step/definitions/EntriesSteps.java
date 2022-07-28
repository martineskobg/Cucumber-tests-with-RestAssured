package step.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntriesSteps {
    Response responseEntries;
    private int expectedEntriesCount;
    private int actualEntriesCount;
    private String entryParameter;
    private final List<Map<Object, Object>> entriesList = new ArrayList<>();

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
        responseEntries = RestAssured.given().get();
        actualEntriesCount = responseEntries.jsonPath().getInt("count");
        System.out.println("Get actual count: " + actualEntriesCount);
    }

    @Then("Verify that expected count is equal to actual")
    public void verify_that_expected_count_is_equal_to_actual() {
        System.out.println("Asser the count");
        Assertions.assertEquals(expectedEntriesCount, actualEntriesCount, "Expected count is not equal to actual count of entries!");
    }

    @Given("Entry parameter {}")
    public void entryParameterParameter(String param) {
        System.out.println("Get parameter: " + param);
        entryParameter = param;
    }

    @When("Get all entries")
    public void getAllEntriesAsAList() {
        String entry;
        responseEntries = RestAssured.given().get();
        for (int i = 0; i < actualEntriesCount; i++) {
            entry = String.format("entries[%d]", i);
            entriesList.add(responseEntries.jsonPath().getMap(entry));
            System.out.println(String.format("Entry whit name: %s has been added to the list " + responseEntries.jsonPath()));
        }
        System.out.println("Get all Entries");
    }

    @Then("Verify that the entry contains the given parameter")
    public void verifyThatTheEntryContainsTheGivenParameter() {
        boolean hasParameter;
        for (Map<Object, Object> entriesMap : entriesList) {
            hasParameter = entriesMap.containsKey(entryParameter);
            Assertions.assertTrue(hasParameter, "The entry doesn't have parameter = " + entryParameter);
        }
    }


}
