
Feature: Verify Go Rest HTTPS Methods using REST-assured

  @GET
  Scenario: GET - Verify API response of users data
    Given User sets the base API request "/users"
    And User authenticates the API request with token
    When User sends the API request to get all the list
    Then User validates the response status is 200
    And User validates the response meta pagination "total" is 1478
    And User validates the response meta pagination "pages" is 74
    And User validates the response meta pagination "page" is 1
    And User validates the response meta pagination "limit" is 20
    And User validates the list body response equals with 20
    And User validates the body response with name "Fr. Dayaanidhi Tandon"


