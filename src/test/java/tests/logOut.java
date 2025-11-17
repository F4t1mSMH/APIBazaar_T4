package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static utilities.ApiUtilities.spec;

public class logOut {

 @Test
 void logoutTest(){

     Response response = given(spec()).post(ConfigReader.getApiBaseUrl() +"/logout");


     response.prettyPrint();
     response
             .then()
             .statusCode(200)
             .contentType(ContentType.JSON)
             .body("message",equalTo("Successfully logged out"))
             ;
 }
}