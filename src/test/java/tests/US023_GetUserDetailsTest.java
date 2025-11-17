package tests;

import base_urls.BazaarBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ApiUtil;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;

public class US023_GetUserDetailsTest extends BazaarBaseUrl {

    @BeforeClass
    public void setUp() {
        loginAsCustomer();
    }

    @Test
    public void getUserDetailsTest() {


        int userId = 356;

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
