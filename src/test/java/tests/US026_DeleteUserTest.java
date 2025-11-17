package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ApiUtil;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;

public class US026_DeleteUserTest {

    @Test
    public void deleteUserSuccessfully() {
        ApiUtil.adminLogin();

        int userId = 4537;
        Response response = given()
                .header("Authorization", "Bearer " + ApiUtil.token)
                .delete(ConfigReader.getApiBaseUrl() + "/users/" + userId);

        response.prettyPrint();
        Assert.assertTrue(response.statusCode() == 200 || response.statusCode() == 204);
    }
}
