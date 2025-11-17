package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateStoreResponse {

    private String success;
    private StoreResponse product;

    public CreateStoreResponse() {}

    public String getSuccess() { return success; }
    public StoreResponse getProduct() { return product; }

    public void setSuccess(String success) { this.success = success; }
    public void setProduct(StoreResponse product) { this.product = product; }
}

