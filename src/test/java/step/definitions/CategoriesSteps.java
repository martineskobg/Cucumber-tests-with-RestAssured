package step.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CategoriesSteps {
    private int expectedCategoriesCount;
    private int actualCountCategories;
    private String category;
    private List<String> categoryList;

    @Given("Api url base url for categories {string}")
    public void api_url_base_url_for_categories(String url) {
        RestAssured.baseURI = url;
        System.out.println("Set a base url: " + url);
    }

    @Given("Expected count of all categories is {int}")
    public void expected_count_of_all_categories_is(int count) {
        expectedCategoriesCount = count;
        System.out.println("Expected count: " + count);
    }

    @When("Get the actual categories count")
    public void get_the_actual_categories_count() {
        actualCountCategories = given()
                .get()
                .jsonPath()
                .getInt("count");

        System.out.println("Get actual count: " + actualCountCategories);
    }

    @Then("Verify that expected categories count is equal to actual count")
    public void verify_that_expected_categories_count_is_equal_to_actual_count() {
        Assertions.assertEquals(expectedCategoriesCount, actualCountCategories,
                String.format("Expected count %d is not equals to actual count %d", expectedCategoriesCount, actualCountCategories));
        System.out.println("Verify that the expected categories count is equal to  the actual count");
    }

    @Given("Category name {}")
    public void categoryName(String category) {
        this.category = category;
        System.out.println("Category: " + category);
    }

    @When("Get all categories names")
    public void get_all_categories_names() {
        categoryList = given()
                .get()
                .jsonPath()
                .getList("categories");
        System.out.println("Get categories List");
    }

    @Then("heck if category is missing")
    public void heck_if_category_is_missing() {
        Assertions.assertTrue(categoryList.contains(category), "The category " + category + " is missing!");
        System.out.println("Check if category is not missing");
    }

}
