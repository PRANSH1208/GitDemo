
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

 Background:
 Given I landed on Ecommerce Page
@Regression
  Scenario Outline: Positive Test of Submitting the order
   # Given Logged in with username "anshika1+1@gmail.com" and Password "Anshika@123!"
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> are submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples: 
      | name  | password | productName|
      | anshika1+1@gmail.com |  Anshika@123! |ZARA COAT 3|
     
