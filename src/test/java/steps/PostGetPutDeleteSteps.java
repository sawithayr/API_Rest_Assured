package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostGetPutDeleteSteps {


    private static final String TOKEN = "26c2934debb54e5000fa6469d822e36565fc83cbf4feb2805444ef18644c50f1";
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
        Assert.assertEquals(response.statusCode(), code, "Something was wrong!");
    }

    @And("User validates the response meta pagination {string} is {int}")
    public void userValidatesTheResponseMetaPaginationIs(String meta, int value) {
        Assert.assertEquals(response.jsonPath().getInt("meta.pagination.'" + meta + "'"), value, "Something was wrong! Please check '" + meta + "'");
    }

    @And("User validates the list body response equals with {int}")
    public void userValidatesTheListBodyResponseEqualsWith(int listData) {
        List<String> jsonResponse = response.jsonPath().getList("data");
        int list = jsonResponse.size();

        Assert.assertEquals(list, listData, "Something was wrong! Please check the list response");
    }

    @When("User create new data user with POST request {string} and request body {string}, {string}, {string}, {string}")
    public void userCreateNewDataUserWithPOSTRequestAndRequestBodyNameGenderEmailStatus(String url, String name, String gender, String email, String status) {

        JSONObject requestParams = new JSONObject();

        requestParams.put("name", name);
        requestParams.put("gender", gender);
        requestParams.put("email", email);
        requestParams.put("status", status);

        request = given().header("Authorization", "Bearer " + TOKEN).contentType(ContentType.JSON).with().body(requestParams);
        request.body(requestParams.toJSONString());
        response = request.post(BASE_URI + url);
        response = request.get().then().log().all().extract().response();
    }

    @And("User validates id is not null")
    public void userValidatesIdIsNotNull() {
        Assert.assertTrue(response.jsonPath().get("data.id") != null, "'data.id'" + " is empty");
    }

    @And("User validates the body response with array name {string}")
    public void userValidatesTheBodyResponseWithArrayName(String name) {
        Assert.assertEquals(response.body().jsonPath().get("data.name[0]"), name, "Something was wrong!");
    }

    @And("User validates the body response with array gender {string}")
    public void userValidatesTheBodyResponseWithArrayGender(String gender) {
        Assert.assertEquals(response.body().jsonPath().get("data.gender[0]"), gender, "Something was wrong!");
    }

    @And("User validates the body response with array email {string}")
    public void userValidatesTheBodyResponseWithArrayEmail(String email) {
        Assert.assertEquals(response.body().jsonPath().get("data.email[0]"), email, "Something was wrong!");
    }

    @And("User validates the body response with array status {string}")
    public void userValidatesTheBodyResponseWithArrayStatus(String status) {
        Assert.assertEquals(response.body().jsonPath().get("data.status[0]"), status, "Something was wrong!");
    }

    @And("User validates the body response with name {string}")
    public void userValidatesTheBodyResponseWithName(String name) {
        Assert.assertEquals(response.body().jsonPath().get("data.name"), name, "Something was wrong!");
    }

    @And("User validates the body response with gender {string}")
    public void userValidatesTheBodyResponseWithGender(String gender) {
        Assert.assertEquals(response.body().jsonPath().get("data.gender"), gender, "Something was wrong!");
    }

    @And("User validates the body response with email {string}")
    public void userValidatesTheBodyResponseWithEmail(String email) {
        Assert.assertEquals(response.body().jsonPath().get("data.email"), email, "Something was wrong!");
    }

    @And("User validates the body response with status {string}")
    public void userValidatesTheBodyResponseWithStatus(String status) {
        Assert.assertEquals(response.body().jsonPath().get("data.status"), status, "Something was wrong!");
    }

    @Given("User gets new data user with GET request {string} with id {string}")
    public void userGetsNewDataUserWithGETRequestWithID(String url, String id) {
        request = given().contentType(ContentType.JSON);
        response = request.get(BASE_URI + url + id).then().log().all().extract().response();

    }

    @When("User create new data user with PUT request {string} and request body {string}, {string}, {string}, {string}")
    public void userCreateNewDataUserWithPUTRequestAndRequestBody(String url, String name, String gender, String email, String status) {
        JSONObject requestParams = new JSONObject();

        requestParams.put("name", name);
        requestParams.put("gender", gender);
        requestParams.put("email", email);
        requestParams.put("status", status);

        request = given().header("Authorization", "Bearer " + TOKEN).contentType(ContentType.JSON).with().body(requestParams);
        request.body(requestParams.toJSONString());
        response = request.put(BASE_URI + url);
        response = request.get().then().log().all().extract().response();
    }

    @When("User gets data user with DELETE request {string}")
    public void userGetsDataUserWithDELETERequest(String url) {
        JSONObject requestParams = new JSONObject();
        request = given().header("Authorization", "Bearer " + TOKEN).contentType(ContentType.JSON).with().body(requestParams);
        request.body(requestParams.toJSONString());
        response = request.delete(BASE_URI + url);
        response = request.get(BASE_URI + url).then().log().all().extract().response();

    }

    @And("User validates the body response with message {string}")
    public void userValidatesTheBodyResponseWithMessage(String word) {
        Assert.assertEquals(response.body().jsonPath().get("data.message"), word, "Something was wrong!");
    }

    @And("User validates the response code is {int}")
    public void userValidatesTheResponseCodeIs(int code) {
        Assert.assertEquals(response.body().jsonPath().getInt("code"), code, "Something was wrong!");
    }
}
