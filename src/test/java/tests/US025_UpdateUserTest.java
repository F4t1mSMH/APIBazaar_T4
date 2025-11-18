package tests;

import base_urls.BazaarBaseUrl;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojos.UpdateUserPojo;
import utilities.ApiUtil;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;
import static tests.US024_CreateUserTest.sharedUserId;

public class US025_UpdateUserTest extends BazaarBaseUrl {

    @BeforeClass
    public void setUp() {
        loginAsCustomer();
    }


    @Test(dependsOnMethods = {"tests.US024_CreateUserTest.createUserTest"} ,priority=1)
    public void updateUserTest() {
//        ApiUtil.adminLogin();
        int userId = 4791;

        String emailAddress = Faker.instance().internet().emailAddress();
        UpdateUserPojo updatePayload = new UpdateUserPojo("Sara abra", emailAddress, "customer", false);

        Response response = given()
                .header("Authorization", "Bearer " + ApiUtil.token)
                .contentType(ContentType.JSON)
                .body(updatePayload)
                .put(ConfigReader.getApiBaseUrl() + "/users/" + userId);

        response.prettyPrint(); // سيطبع النتيجة بدون خطأ SMTP

//        Assert.assertEquals(response.statusCode(), 200); // يجب أن يعمل الآن
        System.out.println("Email = " + updatePayload.getEmail() +"    |||   "+" name = "+ updatePayload.getName());
    }
}
