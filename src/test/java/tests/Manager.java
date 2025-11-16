package tests;

import base_urls.BazaarBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static utilities.ObjectMapperUtils.getJsonNode;
import utilities.ApiUtil;

public class Manager extends BazaarBaseUrl {
    private static int productId;

    @BeforeClass
    public void setUp(){
        loginAsManager();
    }

    @Test(priority = 1)
    void addNewProductTest() {
        JsonNode productPayload = getJsonNode("product");
        String endpoint = "/products/create";
        String body = productPayload.toString();

        Response response = ApiUtil.post(endpoint, body);

        response.prettyPrint();

        response.then()
                .statusCode(201)
                .body("product.name", equalTo(productPayload.get("name").textValue()));

        productId = response.jsonPath().getInt("product.id");
    }

    @Test(priority = 2)
    void updateProductTest() {
        JsonNode updatePayload = getJsonNode("update_product");
        String endpoint = "/products/" + productId;
        String body = updatePayload.toString();

        System.out.println("Updating Product ID: " + productId);

        Response response = ApiUtil.put(endpoint, body);

        response.prettyPrint();

        response.then()
                .statusCode(200)
                .body("product.name", equalTo(updatePayload.get("name").textValue()))
                .body("product.price", equalTo(updatePayload.get("price").intValue()));
    }

    @Test(priority = 3)
    void deleteProductTest() {
        String endpoint = "/products/" + productId;
        System.out.println("Deleting Product ID: " + productId);
        Response response = ApiUtil.delete(endpoint);
        response.prettyPrint();
        response.then()
                .statusCode(200)
                .body("success", equalTo("Product deleted successfully!"));
    }
}