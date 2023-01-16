package com.glue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.Customer;

import java.io.IOException;

public class GetTest {
    RequestSpecification res;
    Response response;
    Customer customer = new Customer();
    @Given("The user needs a token to get the list")
    public void makingResponse() throws IOException {
        res = customer.sendToken();
    }
    @When("Get the crocodile list")
    public void postingTheKeys() throws IOException {
        response = customer.getCrocodilesList(res);
    }

    @Then("the API should allow the user to see the crocodile list")
    public void statusCodeCorrect() throws IOException {
        response = customer.statusCodeCrocodile(response);
    }
}
