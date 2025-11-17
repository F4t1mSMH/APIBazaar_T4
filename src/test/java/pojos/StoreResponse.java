package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreResponse {

    private int id;
    private String name;
    private String description;
    private String location;
    private int admin_id;

    public StoreResponse() {
    }

    public StoreResponse(int id, String name, String description, String location, int admin_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.admin_id = admin_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    @Override
    public String toString() {
        return "StoreResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", admin_id=" + admin_id +
                '}';
    }
}
