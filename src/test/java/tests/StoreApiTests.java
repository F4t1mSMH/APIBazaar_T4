package tests;


import base_urls.BazaarBaseUrl;
import io.restassured.response.Response;

import org.testng.annotations.Test;
import pojos.CreateStoreRequest;
import pojos.CreateStoreResponse;
import pojos.StoreResponse;
import pojos.UpdateStoreRequest;
import utilities.ApiUtil;
import utilities.ObjectMapperUtils;

import static org.junit.Assert.*;

public class StoreApiTests {
    private static int sharedStoreId;

    @Test(priority = 1)
    public void TC_US017_001_getAllStores() {


        BazaarBaseUrl baseUrl = new BazaarBaseUrl();
        baseUrl.loginAsAdmin();


        Response response = ApiUtil.get("/stores");


        ApiUtil.verifyStatusCode(response, 200);


        response.then().assertThat().body("size()", org.hamcrest.Matchers.greaterThan(0));

        System.out.println("All stores retrieved successfully.");
        System.out.println(response.prettyPrint());
    }


    @Test(priority = 2)
    public void TC_US019_001_createStoreValid() {


        BazaarBaseUrl baseUrl = new BazaarBaseUrl();
        baseUrl.loginAsAdmin();


        CreateStoreRequest storeBody = new CreateStoreRequest(
                "Store A",
                "test",
                "Riyadh",
                352
        );


        Response response = ApiUtil.post("/stores/create", storeBody);
        response.prettyPrint();


        ApiUtil.verifyStatusCode(response, 201);


        CreateStoreResponse storeResponse = response.as(CreateStoreResponse.class);
        sharedStoreId = storeResponse.getProduct().getId();



        assertEquals("Store created successfully!", storeResponse.getSuccess());
        assertEquals("Store A", storeResponse.getProduct().getName());
        assertEquals("test", storeResponse.getProduct().getDescription());
        assertEquals("Riyadh", storeResponse.getProduct().getLocation());


        int newStoreId = storeResponse.getProduct().getId();
        System.out.println("Created Store ID = " + newStoreId);
        System.out.println("Shared Store ID = " + sharedStoreId);
    }

    @Test(priority = 3)
    public void TC_US018_001_getStoreById() {


        BazaarBaseUrl baseUrl = new BazaarBaseUrl();
        baseUrl.loginAsAdmin();


        int storeId = sharedStoreId;
        Response response = ApiUtil.get("/stores/" + storeId);
        response.prettyPrint();


        ApiUtil.verifyStatusCode(response, 200);


        StoreResponse store = response.as(StoreResponse.class);


        assertEquals(storeId, store.getId());
        assertNotNull(store.getName());
        assertNotNull(store.getDescription());
        assertNotNull(store.getLocation());

        System.out.println(store.toString());
    }


    @Test(priority = 4)
    public void TC_US018_002_invalidStoreId() {


        BazaarBaseUrl baseUrl = new BazaarBaseUrl();
        baseUrl.loginAsAdmin();


        int invalidStoreId = 99999;


        Response response = ApiUtil.get("/stores/" + invalidStoreId);


        response.prettyPrint();


        ApiUtil.verifyStatusCode(response, 404);


        String errorMessage = response.jsonPath().getString("error");
        assertEquals("Store not found", errorMessage);
    }





    @Test(priority = 5)
    public void TC_US019_002_createStoreInvalidData() {


        BazaarBaseUrl baseUrl = new BazaarBaseUrl();
        baseUrl.loginAsAdmin();


        CreateStoreRequest invalidStore = new CreateStoreRequest(
                "",
                "test",
                "",
                352
        );


        Response response = ApiUtil.post("/stores/create", invalidStore);
        response.prettyPrint();


        ApiUtil.verifyStatusCode(response, 422);


        String message = response.jsonPath().getString("message");
        assertNotNull(message);




        assertNotNull(response.jsonPath().get("errors"));


    }


    @Test(priority = 6)
    public void TC_US019_003_createStoreServerError() {


        BazaarBaseUrl baseUrl = new BazaarBaseUrl();
        baseUrl.loginAsAdmin();


        CreateStoreRequest invalidAdminData = new CreateStoreRequest(
                "Store A",
                "test server failure",
                "Riyadh",
                999999
        );


        Response response = ApiUtil.post("/stores/create", invalidAdminData);

       int statusCode = response.statusCode();
        System.out.println("statusCode = " + statusCode);
        response.prettyPrint();


        ApiUtil.verifyStatusCode(response, 500);
    }

    @Test(priority = 7)
    public void TC_US020_001_updateStoreSuccessfully() {


        BazaarBaseUrl baseUrl = new BazaarBaseUrl();
        baseUrl.loginAsAdmin();

        int storeId = sharedStoreId;


        UpdateStoreRequest updateBody = new UpdateStoreRequest(
                "Updated Store Name",
                "Updated Description",
                "Riyadh",
                352
        );


//        String updateJson = ObjectMapperUtils.convertObjectToJson(updateBody);
        String updateJson = ObjectMapperUtils.convertToJson(updateBody);


        Response updateResponse = ApiUtil.put("/stores/" + storeId, updateJson);
        updateResponse.prettyPrint();


        ApiUtil.verifyStatusCode(updateResponse, 200);


        assertEquals("Updated Store Name", updateResponse.jsonPath().getString("name"));
        assertEquals("Updated Description", updateResponse.jsonPath().getString("description"));
        assertEquals("Riyadh", updateResponse.jsonPath().getString("location"));
    }


    @Test(priority = 8)
    public void TC_US020_002_updateNonExistingStore() {


        BazaarBaseUrl baseUrl = new BazaarBaseUrl();
        baseUrl.loginAsAdmin();


        UpdateStoreRequest updateBody = new UpdateStoreRequest(
                "Updated Name",
                "Updated Description",
                "Dammam",
                352
        );


        String updateJson = ObjectMapperUtils.convertToJson(updateBody);


        int invalidId = 99999;

        Response response = ApiUtil.put("/stores/" + invalidId, updateJson);

        response.prettyPrint();


        ApiUtil.verifyStatusCode(response, 404);


        assertEquals("Store not found", response.jsonPath().getString("error"));
    }


    @Test(priority = 9)
    public void TC_US020_003_updateStoreInvalidData() {


        BazaarBaseUrl baseUrl = new BazaarBaseUrl();
        baseUrl.loginAsAdmin();


        CreateStoreRequest createBody = new CreateStoreRequest(
                "Store Before Invalid Update",
                "Initial Description",
                "Jeddah",
                352
        );

        Response createResponse = ApiUtil.post("/stores/create", createBody);
        ApiUtil.verifyStatusCode(createResponse, 201);

        int storeId = createResponse.jsonPath().getInt("product.id");
        System.out.println("Store created with ID: " + storeId);
        createResponse.prettyPrint();



        UpdateStoreRequest invalidUpdateBody = new UpdateStoreRequest(
                "",
                "Updated Desc",
                "Riyadh",
                352
        );


        String updateJson = ObjectMapperUtils.convertToJson(invalidUpdateBody);


        Response response = ApiUtil.put("/stores/" + storeId, updateJson);
        response.prettyPrint();



        ApiUtil.verifyStatusCode(response, 422);


        assertTrue(response.asString().contains("The name field is required"));
    }

    @Test(priority = 10)
    public void TC_US020_004_updateStoreServerFailure() {


        BazaarBaseUrl baseUrl = new BazaarBaseUrl();
        baseUrl.loginAsAdmin();

        int storeId = sharedStoreId;



        UpdateStoreRequest failingUpdateBody = new UpdateStoreRequest(
                "Valid Name",
                "Valid Desc",
                "Riyadh",
                99999999
        );

        String failingJson = ObjectMapperUtils.convertToJson(failingUpdateBody);


        Response response = ApiUtil.put("/stores/" + storeId, failingJson);
        response.prettyPrint();


        ApiUtil.verifyStatusCode(response, 500);
    }

    @Test(priority = 11)
    public void TC_US021_001_deleteStoreSuccessfully() {


        BazaarBaseUrl baseUrl = new BazaarBaseUrl();
        baseUrl.loginAsAdmin();

        int storeId = sharedStoreId;




        Response deleteResponse = ApiUtil.delete("/stores/" + storeId);
        deleteResponse.prettyPrint();



        ApiUtil.verifyStatusCode(deleteResponse, 200);


        String message = deleteResponse.jsonPath().getString("success");
        assertEquals("Store deleted successfully!", message);
    }


    @Test(priority = 12)
    public void TC_US021_002_deleteStoreServerError() {


        BazaarBaseUrl baseUrl = new BazaarBaseUrl();
        baseUrl.loginAsAdmin();


        int problematicId = 1;


        Response response = ApiUtil.delete("/stores/" + problematicId);

        response.prettyPrint();
        ApiUtil.verifyStatusCode(response, 500);
    }




}