package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasKey;
import static utilities.ApiUtilities.spec;

public class CurrentUserProfile {

    @Test
    void currentUserProfileTest(){

//        {
//            "id": 1,
//                "name": "John Doe",
//                "email": "user@example.com",
//                "role": "admin"
//        }

//        BazaarPojo expectedData=new BazaarPojo(3881, "John Doe", "user@example.com","admin");
//        System.out.println("expectedData"+expectedData);

        Response response = given(spec()).get(ConfigReader.getApiBaseUrl() +"/me");

        response.then()
                .statusCode(200)
                .body("",allOf(
                        hasKey("id"),
                                hasKey("name"),
                                hasKey("email"),
                                hasKey("role"))
                        );
    }
}
