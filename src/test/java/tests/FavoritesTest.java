package tests;

import base_urls.BazaarBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojos.FavoriteResponse;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;
import static utilities.ApiUtilities.spec;
import static utilities.ObjectMapperUtils.getJsonNode;

public class FavoritesTest extends BazaarBaseUrl {

    @BeforeClass
    public void setUp() {
        loginAsCustomer();
    }

///-------------------------------------------ADD----------------------------
    @Test (priority = 1)
    public void addProductToFavorites() {

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("product_id", 211);

        Response response = given(spec())
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/favorites/create");

        response.prettyPrint();
        int statusCode = response.getStatusCode();

        if (statusCode == 200) {

            FavoriteResponse favoriteResponse = response.as(FavoriteResponse.class);
            System.out.println("Added favorite: " + favoriteResponse);


            Assert.assertNotNull(favoriteResponse.getId(), "Favorite ID should not be null");
            Assert.assertEquals(favoriteResponse.getProductId(), 398, "Product ID should match the added product");
            Assert.assertNull(favoriteResponse.getError(), "Error field should be null on success");

            System.out.println(" Product added to favorites successfully. Favorite ID: " + favoriteResponse.getId());

        } else if (statusCode == 400) {

            String errorMessage = response.jsonPath().getString("error");
            Assert.assertTrue(errorMessage.contains("already in favorites"), "Error should indicate product already exists");

            System.out.println(" Product already in favorites: " + errorMessage);
        } else {

            Assert.fail("Unexpected status code: " + statusCode);
        }
    }

    //multifavorites
    @Test
    public void addMultipleFavoritesFromJson() {


        JsonNode AddDFavoriateEproduct = getJsonNode("multiFavorites");

        // 2️⃣ Loop through each product and send POST request
        for (JsonNode productNode : AddDFavoriateEproduct) {
            int productId = productNode.get("product_id").asInt();

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("product_id", productId);

            Response response = given(spec())
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .post("/favorites/create");

            response.prettyPrint();

            int statusCode = response.getStatusCode();
            String successMessage = response.jsonPath().getString("success");
            String errorMessage = response.jsonPath().getString("error");

            if (statusCode == 200) {
                System.out.println(" Product ID " + productId + " added successfully: " + successMessage);
            } else if (statusCode == 400) {
                System.out.println("Product ID " + productId + " is already in favorites: " + errorMessage);
            } else {
                System.out.println(" Unexpected status code " + statusCode + " for product ID " + productId);
            }
        }
    }

    ///-------------------------------------------VIEW-----------------------------------------------

    @Test(priority = 2)
    public void ViewProductFavorites() {
        Response response = given(spec())
                .contentType(ContentType.JSON)
                .get("/favorites");
        response.prettyPrint();
        response.then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(0))
                .body("[0].product.name", notNullValue())
                .body("[0].product.stock", greaterThan(0));


}

    ///-------------------------------------------DELETE-----------------------------------------------

    @Test(priority = 3)
    public void removeProductFromFavorites() {
        int favoriteId = 211;


        Response response = given(spec())
                .contentType(ContentType.JSON)
                .delete("/favorites/" + favoriteId);

        response.prettyPrint();

        int statusCode = response.getStatusCode();

        if (statusCode == 200) {

            FavoriteResponse deleteResponse = response.as(FavoriteResponse.class);

            Assert.assertNotNull(deleteResponse.getSuccess(), "Success message should not be null");
            Assert.assertNull(deleteResponse.getError(), "Error field should be null");
            System.out.println("Favorite removed successfully:" + deleteResponse.getSuccess());

        } else if (statusCode == 404) {
            FavoriteResponse deleteResponse = response.as(FavoriteResponse.class);

            Assert.assertNotNull(deleteResponse.getError(), "Error message should not be null");
            System.out.println("Favorite not found: " + deleteResponse.getError());

        } else {
            Assert.fail("Unexpected status code: " + statusCode);
        }
    }


}
