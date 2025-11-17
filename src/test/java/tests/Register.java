package tests;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertEquals;

public class Register {

    @Test
    void registerTest() {
//        Response response = RestAssured.get("https://bazaarstores.com/api/register/4058");
//
//        response.prettyPrint();
//
//        Assert.assertEquals(response.statusCode(), 201,"Status code is not 201");
//
//        response
//                .then()
//                .body("user",hashKey("name"))
//                ;
//
//    }
        String payload = """
                {
                       "name": "John Doe",
                           "email": "%s",
                           "password": "password",
                           "password_confirmation": "password"
                   }
                """;
        payload=String.format(payload, Faker.instance().internet().emailAddress());
        Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).post(ConfigReader.getApiBaseUrl()+ "/register");

        response.prettyPrint();
        response
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("user.name",equalTo("John Doe"))
        ;

    }
    @Test
    void dublicateEmailTest() {
        String payload = """
                {
                       "name": "John Doe",
                           "email": "admin@sda.com",
                           "password": "password",
                           "password_confirmation": "password"
                   }
                """;
        payload=String.format(payload, Faker.instance().internet().emailAddress());
        Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).post(ConfigReader.getApiBaseUrl()+ "/register");

        response.prettyPrint();
        response
                .then()
                .statusCode(422)
        ;

    }


    @Test
    void missingRequiredFieldsTest() {
        String payload = """
                {
                       "name": "John Doe",
                           "password": "password",
                           "password_confirmation": "password"
                   }
                """;
        payload=String.format(payload, Faker.instance().internet().emailAddress());
        Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).post(ConfigReader.getApiBaseUrl()+ "/register");

        response.prettyPrint();
        response
                .then()
                .statusCode(422)
        ;

    }

    @Test
    void invalidEmailTest() {
        String payload = """
                {
                       "name": "John Doe",
                         "email": "adminsda.com",
                           "password": "password",
                           "password_confirmation": "password"
                   }
                """;
        payload=String.format(payload, Faker.instance().internet().emailAddress());
        Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).post(ConfigReader.getApiBaseUrl()+ "/register");

        response.prettyPrint();
        response
                .then()
                .statusCode(422)

        ;

    }
}