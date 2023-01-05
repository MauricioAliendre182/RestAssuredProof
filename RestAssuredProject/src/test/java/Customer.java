import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public String username;
    public String password;
    public void callingLoginAPI() throws IOException, ConfigurationException {
        prop.load(file);
        RestAssured.baseURI  = prop.getProperty("baseUrl");
        username = prop.getProperty("username");
        password = prop.getProperty("password");
        Response res =
                given()
                        .contentType("application/json")
                        .body(
                                String.format(
                                        "{" +
                                                "\"username\":%s, "+
                                                "\"password\":%s" +
                                        "}"
                                        , username, password
                                )
                        ).
                        when()
                        .post("/auth/token/login/").
                        then()
                        .assertThat().statusCode( 200 ).extract().response();

        JsonPath jsonpath = res.jsonPath();
        token = jsonpath.get("access");
        Utils.setEnvVariableToken(token);
    }
    public void customerList() throws IOException {
        prop.load(file);
        RestAssured.baseURI  = prop.getProperty("baseUrl");
        Response res =
                (Response) given()
                        .contentType("application/json").header("Authorization","Bearer " + prop.getProperty("Token")).
                        when()
                        .get("/my/crocodiles/").
                        then()
                        .assertThat().statusCode( 200 ).extract().response();

        JsonPath response = res.jsonPath();
        assertEquals(response.get("[0].id").toString(),"12223871");
        System.out.println("Status Code: " + res.statusCode());
        System.out.println("Body: " + res.asString());
    }

}
