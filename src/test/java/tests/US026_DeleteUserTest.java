package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ApiUtil;
import utilities.ConfigReader;
import static io.restassured.RestAssured.given;
import static tests.US024_CreateUserTest.sharedUserId;

public class US026_DeleteUserTest {

    @Test(dependsOnMethods = {"tests.US024_CreateUserTest.createUserTest"} ,priority=2)
    public void deleteUserSuccessfully() {
        ApiUtil.adminLogin();

        int userId = sharedUserId;
        Response response = given()
                .header("Authorization", "Bearer " + ApiUtil.token)
                .delete(ConfigReader.getApiBaseUrl() + "/users/" + userId);

        response.prettyPrint();
        Assert.assertTrue(response.statusCode() == 200 || response.statusCode() == 204);
    }
}
