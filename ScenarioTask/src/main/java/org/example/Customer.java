package org.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.configuration.ConfigurationException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Customer {
    Properties prop=new Properties();
    FileInputStream file;
    {
        try
        {
            file = new FileInputStream("./src/test/resources/config.properties");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public String token;

    public RequestSpecification makingRequestSpecification(String username, String password) throws IOException {
        prop.load(file);
        RestAssured.baseURI  = prop.getProperty("baseUrl");
        RequestSpecification res = given().contentType("application/json")
                .body(
                        String.format(
                                "{" +
                                        "\"username\":\"%s\", "+
                                        "\"password\":\"%s\"" +
                                        "}"
                                , username, password
                        ));
        return res;
    }

    public Response postingUsernameAndPassword(RequestSpecification res) throws IOException {
        prop.load(file);
        RestAssured.baseURI  = prop.getProperty("baseUrl");
        Response response = res.when().post("/auth/token/login/");
        return response;
    }

    public Response correctStatusCode(Response response) throws IOException {
        prop.load(file);
        RestAssured.baseURI  = prop.getProperty("baseUrl");
        Response responseFinal = response.then().assertThat().statusCode( 200 ).extract().response();
        return responseFinal;
    }

    public void writeTokenInProperties(Response response) throws ConfigurationException, IOException {
        prop.load(file);
        RestAssured.baseURI  = prop.getProperty("baseUrl");
        JsonPath jsonpath = response.jsonPath();
        token = jsonpath.get("access");
        Utils.setEnvVariableToken(token);
    }

    public RequestSpecification sendToken() throws IOException {
        prop.load(file);
        RestAssured.baseURI  = prop.getProperty("baseUrl");
        RequestSpecification res = given().contentType("application/json").header("Authorization","Bearer " + prop.getProperty("Token"));
        return res;
    }

    public Response getCrocodilesList(RequestSpecification res) throws IOException {
        prop.load(file);
        RestAssured.baseURI  = prop.getProperty("baseUrl");
        Response response = res.when().get("/my/crocodiles/");
        return response;
    }

    public Response statusCodeCrocodile(Response response) throws IOException {
        prop.load(file);
        RestAssured.baseURI  = prop.getProperty("baseUrl");
        Response responseFinal = response.then().assertThat().statusCode( 200 ).extract().response();
        return responseFinal;
    }

}
