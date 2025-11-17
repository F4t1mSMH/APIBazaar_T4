package pojos;

public class CreateStoreRequest {

    private String name;
    private String description;
    private String location;
    private int admin_id;

    public CreateStoreRequest() {}

    public CreateStoreRequest(String name, String description, String location, int admin_id) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.admin_id = admin_id;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public int getAdmin_id() { return admin_id; }

    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setLocation(String location) { this.location = location; }
    public void setAdmin_id(int admin_id) { this.admin_id = admin_id; }
}

