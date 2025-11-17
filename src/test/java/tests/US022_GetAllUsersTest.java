package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ApiUtil;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;

public class US022_GetAllUsersTest {

    @Test
    public void getAllUsersTest() {
        ApiUtil.adminLogin(); // تسجيل الدخول مرة واحدة

        Response response = given()
                .header("Authorization", "Bearer " + ApiUtil.token)
                .contentType(ContentType.JSON)
                .get(ConfigReader.getApiBaseUrl() + "/users");

        response.prettyPrint();
        Assert.assertEquals(response.statusCode(), 200);
    }
}
