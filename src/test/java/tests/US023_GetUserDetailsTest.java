package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ApiUtil;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;

public class US023_GetUserDetailsTest {

    @Test
    public void getUserDetailsTest() {
        ApiUtil.adminLogin();

        int userId = 4316;

        Response response = given()
                .header("Authorization", "Bearer " + ApiUtil.token)
                .get(ConfigReader.getApiBaseUrl() + "/users/" + userId);

        response.prettyPrint();
        Assert.assertEquals(response.statusCode(), 200);

        // طباعة البيانات
        String name = response.jsonPath().getString("user.name");
        String email = response.jsonPath().getString("user.email");
        String role = response.jsonPath().getString("user.role");


    }
}
