Feature: Login with a user
  Scenario Outline: Login with a username and password
    Given a user needs a "<username>" and "<password>" to login
    When the username and password are introduced
    Then the API should allow the user to login with a OK status code
    And write the token in the config.properties
    Examples:
      |username       |password                    |
      |Mauricio44     |APITestingProofMauricio     |
      |LosJucumaris123|LosJucumarisJalaUniversity34|
    
  Scenario: Get the list of crocodiles
    Given The user needs a token to get the list
    When Get the crocodile list
    Then the API should allow the user to see the crocodile list
