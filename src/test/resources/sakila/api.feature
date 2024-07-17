Feature: Sakila Api returns list of actors

  Rule: There should be max 10 results per page
  Scenario: Get actors on first page
    Given the server is running
    When I send a GET request to /api/actors
    Then the response status should be 200
    And the response should contain at most 10 actors

    Scenario: Get films on first page
      Given the server is running
      When I send a GET request to /api/films
      Then the response status should be 200
      And the response should contain at most 10 films


  Rule: Actors should Have both a firstname and a lastname
    Scenario: Get actor by id
      Given the server is running
      When I send a GET request to /api/actors/2
      Then the response status should be 302
      And the actor should have a non empty first and last name


  Rule: Films should have a title
    Scenario: Get film by id
      Given the server is running
      When I send a GET request to /api/films/100
      Then the response status should be 302
      And the film should have a non empty title

  Rule: Actors should be retaled to a films they are in
  Scenario:
