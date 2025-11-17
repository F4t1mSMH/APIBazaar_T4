package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.CreateUserPojo;
import utilities.ApiUtil;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;

public class US024_CreateUserTest {

    @Test
    public void createUserTest() {
        ApiUtil.adminLogin();

        CreateUserPojo payload = new CreateUserPojo("Test User", "testuser@example.com", "customer");

        Response response = given()
                .header("Authorization", "Bearer " + ApiUtil.token)
                .contentType(ContentType.JSON)
                .body(payload)
                .post(ConfigReader.getApiBaseUrl() + "/users");

        response.prettyPrint();
        Assert.assertEquals(response.statusCode(), 405);
    }
}
