package pojos;

public class UserDetailsPojo {
    private int id;
    private String name;
    private String email;
    private String role;

    public UserDetailsPojo(){}

    // Getters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getRole() { return role; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setRole(String role) { this.role = role; }
}


