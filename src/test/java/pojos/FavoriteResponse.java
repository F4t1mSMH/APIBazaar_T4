package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FavoriteResponse {

    private Integer id;

    @JsonProperty("product_id")
    private Integer productId;

    @JsonProperty("user_id")
    private Integer userId;

    private String error;

    private String success; // <-- Add this

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "FavoriteResponse{" +
                "id=" + id +
                ", productId=" + productId +
                ", userId=" + userId +
                ", error='" + error + '\'' +
                ", success='" + success + '\'' +
                '}';
    }
}
