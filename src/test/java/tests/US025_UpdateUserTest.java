package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.UpdateUserPojo;
import utilities.ApiUtil;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;

public class US025_UpdateUserTest {

    @Test
    public void updateUserTest() {
        ApiUtil.adminLogin();

        int userId = 4514;

        UpdateUserPojo updatePayload = new UpdateUserPojo("Sara abra", "Sara.ebr4a@example.com", "customer", false);

        Response response = given()
                .header("Authorization", "Bearer " + ApiUtil.token)
                .contentType(ContentType.JSON)
                .body(updatePayload)
                .put(ConfigReader.getApiBaseUrl() + "/users/" + userId);

        response.prettyPrint(); // سيطبع النتيجة بدون خطأ SMTP
        Assert.assertEquals(response.statusCode(), 200); // يجب أن يعمل الآن

    }
}
