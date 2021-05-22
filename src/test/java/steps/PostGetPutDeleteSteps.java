package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostGetPutDeleteSteps {

    private static final String TOKEN = "15468ae613c31ba7242ea5f19c85d6bc18d60874897e0e872501e866c0b271e6";
    private static final String BASE_URI = "https://gorest.co.in/public-api";

    RequestSpecification request;
    Response response;

    @Given("User sets the base API request {string}")
    public void userSetsTheBaseAPIRequest(String url) {
        RestAssured.baseURI = BASE_URI + url;
    }

    @And("User authenticates the API request with token")
    public void userAuthenticatesTheAPIRequestWithToken() {
        request = given().auth().oauth2(TOKEN);
    }

    @When("User sends the API request to get all the list")
    public void userSendsTheAPIRequestToGetAllTheList() {
        response = request.get().then().log().all().extract().response();
    }

    @Then("User validates the response status is {int}")
    public void userValidatesTheResponseStatusIs(int code) {
        Assert.assertEquals(code, response.statusCode(), "Something was wrong!");
    }

    @And("User validates the body response with name {string}")
    public void userValidatesTheBodyResponseWithName(String name) {
        Assert.assertEquals(response.body().jsonPath().get("data.name[2]"), name, "Something was wrong! Response name not equals with '"+ name +"'! ");
    }

    @And("User validates the response meta pagination {string} is {int}")
    public void userValidatesTheResponseMetaPaginationIs(String meta, int value) {
        Assert.assertEquals(response.jsonPath().getInt("meta.pagination.'"+ meta +"'"), value, "Something was wrong! Please check the value number");
    }

    @And("User validates the list body response equals with {int}")
    public void userValidatesTheListBodyResponseEqualsWith(int listData) {
        List<String> jsonResponse = response.jsonPath().getList("data");
        int list = jsonResponse.size();

        Assert.assertEquals(list, listData, "Something was wrong! Please check the list response");
    }
}
