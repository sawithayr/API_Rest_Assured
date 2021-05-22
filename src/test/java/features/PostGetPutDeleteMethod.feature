Feature: Verify Go Rest HTTPS Methods using REST-assured

  @getAllListUsers
  Scenario: GET - Verify API response of users data
    Given User sets the base API request "/users"
    And User authenticates the API request with token
    When User sends the API request to get all the list
    Then User validates the response status is 200
    And User validates the response meta pagination "total" is 1558
    And User validates the response meta pagination "pages" is 78
    And User validates the response meta pagination "page" is 1
    And User validates the response meta pagination "limit" is 20
    And User validates the list body response equals with 20

  @createNewUser
  Scenario Outline: POST - Create new user
    Given User sets the base API request "/users"
    When User create new data user with POST request "/users" and request body "<name>", "<gender>", "<email>", "<status>"
    Then User validates the response status is 200
    And User validates id is not null
    And User validates the body response with array name "<name>"
    And User validates the body response with array gender "<gender>"
    And User validates the body response with array email "<email>"
    And User validates the body response with array status "<status>"

    Examples:
      | name          | gender | email                  | status |
      | Justin Bieber | Male   | justin.bieber@fake.com | Active |

  @getDataNewUser
  Scenario: GET - Get data new user details
    Given User gets new data user with GET request "/users" with id "/1693"
    Then User validates the response status is 200
    And User validates id is not null
    And User validates the body response with name "Justin Bieber"
    And User validates the body response with gender "Male"
    And User validates the body response with email "justin.bieber@fake.com"
    And User validates the body response with status "Active"




