package tests;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.CreateUserPojo;
import utilities.ApiUtil;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;

public class US024_CreateUserTest {
    public static int sharedUserId;
    @Test
    public void createUserTest() {
//        ApiUtil.adminLogin();
//
//        CreateUserPojo payload = new CreateUserPojo("Test User", "testuser@example.com", "customer");
//
//        Response response = given()
//                .header("Authorization", "Bearer " + ApiUtil.token)
//                .contentType(ContentType.JSON)
//                .body(payload)
//                .post(ConfigReader.getApiBaseUrl() + "/users");
//
//        response.prettyPrint();
//        Assert.assertEquals(response.statusCode(), 405);

        String payload = """
            {
                "name": "John Doe",
                "email": "%s",
                "password": "password",
                "password_confirmation": "password"
            }
            """;

        payload = String.format(payload, Faker.instance().internet().emailAddress());

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .post(ConfigReader.getApiBaseUrl() + "/register");

        response.prettyPrint();

        response.then()
                .statusCode(201)
                .contentType(ContentType.JSON);

        sharedUserId = response.jsonPath().getInt("user.id");
        System.out.println("Created User ID: " + sharedUserId);
    }
}
