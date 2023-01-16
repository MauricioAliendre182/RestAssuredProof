package com.glue;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.configuration.ConfigurationException;
import org.example.Customer;

import java.io.IOException;

public class RunTest {
    RequestSpecification res;
    Response response;
    Customer customer = new Customer();
    @Given("a user needs a {string} and {string} to login")
    public void makingResponse(String username, String password) throws IOException {
        res = customer.makingRequestSpecification(username, password);
    }
    @When("the username and password are introduced")
    public void postingTheKeys() throws IOException {
       response = customer.postingUsernameAndPassword(res);
    }

    @Then("the API should allow the user to login with a OK status code")
    public void statusCodeCorrect() throws IOException {
        response = customer.correctStatusCode(response);
    }

    @And("write the token in the config.properties")
    public void writeToken() throws ConfigurationException, IOException {
        customer.writeTokenInProperties(response);
    }

//    @Test
//    public void customerList() throws IOException {
//        Customer customer=new Customer();
//        customer.customerList();
//    }
}
