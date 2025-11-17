package pojos;

public class UpdateStoreRequest {

    private String name;
    private String description;
    private String location;
    private int admin_d;

    public UpdateStoreRequest() {}

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

    public int getAdmin_d() {
        return admin_d;
    }

    public void setAdmin_d(int admin_d) {
        this.admin_d = admin_d;
    }

    public UpdateStoreRequest(String name, String description, String location, int admin_id) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.admin_d = admin_id;
    }



    @Override
    public String toString() {
        return "UpdateStoreRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", admin_id=" + admin_d +
                '}';
    }
}
