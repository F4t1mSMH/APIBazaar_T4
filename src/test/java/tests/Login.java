package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Login  {

    @Test
    void loginTest(){

        String payload ="""

                {
                  "email": "admin@sda.com",
                  "password": "Password.12345"
                }
                """;
        Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).post(ConfigReader.getApiBaseUrl() +"/login");
       response.prettyPrint();
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("user.email",allOf(
                        notNullValue(),
                        not(emptyString()),
                        instanceOf(String.class)
                ));

    }
    @Test
    void negativeLoginTest(){

        String payload ="""

                {
                  "email": "adminX@sda.com",
                  "password": "Password.12345"
                }
                """;
        Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).post(ConfigReader.getApiBaseUrl() +"/login");
        response.prettyPrint();
        response
                .then()
                .statusCode(401)
                .contentType(ContentType.JSON)
                .body("error",equalTo("Invalid credentials")
                );
    }
}