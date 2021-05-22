Feature: Verify Go Rest HTTPS Methods using REST-assured

  //Get user details

  @GET
  Scenario: GET - Verify API response of users data
    Given User sets the base API request "/users"
    And User authenticates the API request with token
    When User sends the API request to get all the list
    Then User validates the response status is 200
    And User validates the response meta pagination "total" is 1484
    And User validates the response meta pagination "pages" is 75
    And User validates the response meta pagination "page" is 1
    And User validates the response meta pagination "limit" is 20
    And User validates the list body response equals with 20
#    And User validates the body response with name "Fr. Dayaanidhi Tandon"

  @POST
  Scenario Outline: POST - Create new user
    Given User sets the base API request "/users"
    When User create new data user with POST request "/users" and request body "<name>", "<gender>", "<email>", "<status>"
    Then User validates the response status is 200
    And User validates id is not null
    And User validates the body response with name "<name>"
    And User validates the body response with gender "<gender>"
    And User validates the body response with email "<email>"
    And User validates the body response with status "<status>"

    Examples:
      | name          | gender | email                  | status |
      | Justin Bieber | Male   | justin.bieber@fake.com | Active |

