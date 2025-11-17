package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FavoritePojo {

    private int id;

    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("product_id")
    private int productId;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    private ProductPojo product;

    // Getters
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getProductId() {
        return productId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public ProductPojo getProduct() {
        return product;
    }
}
