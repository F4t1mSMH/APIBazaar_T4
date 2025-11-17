package tests;

import base_urls.BazaarBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static utilities.ObjectMapperUtils.getJsonNode;
import utilities.ApiUtil;

public class ManagerNegativeTest extends BazaarBaseUrl {
    private static final int NON_EXISTENT_ID = 999999;

    @BeforeClass
    public void setUp(){
        loginAsManager();
    }

    @Test(priority = 1)
    void addNewProductMissingRequiredFieldTest() {
        String invalidBody = "{\"name\": \"Product Missing Fields\", \"description\": \"Test\"}";
        String endpoint = "/products/create";

        Response response = ApiUtil.post(endpoint, invalidBody);

        response.prettyPrint();

        response.then()
                .statusCode(422)
                .body("errors.price[0]", equalTo("The price field is required."));
    }


    @Test(priority = 2)
    void updateProductNotFoundTest() {
        JsonNode updatePayload = getJsonNode("update_product");
        String endpoint = "/products/" + NON_EXISTENT_ID;
        String body = updatePayload.toString();

        System.out.println("Attempting to Update Non-Existent Product ID: " + NON_EXISTENT_ID);

        Response response = ApiUtil.put(endpoint, body);

        response.prettyPrint();

        response.then()
                .statusCode(500)
                .body("error", equalTo("Product update failed. Please try again."));
    }


    @Test(priority = 3)
    void updateProductValidationFailsTest() {
        String invalidUpdateBody = "{\"name\": \"Missing Required Fields\"}";
        String endpoint = "/products/1";

        System.out.println("Attempting to Update Product ID: 1 with Invalid Data");

        Response response = ApiUtil.put(endpoint, invalidUpdateBody);

        response.prettyPrint();

        response.then()
                .statusCode(422)
                .body("errors.stock[0]", equalTo("The stock field is required."));
    }

    @Test(priority = 4)
    void deleteProductNotFoundTest() {
        String endpoint = "/products/" + NON_EXISTENT_ID;

        System.out.println("Attempting to Delete Non-Existent Product ID: " + NON_EXISTENT_ID);

        Response response = ApiUtil.delete(endpoint);

        response.prettyPrint();

        response.then()
                .statusCode(500)
                .body("error", equalTo("Product deletion failed. Please try again."));
    }
}