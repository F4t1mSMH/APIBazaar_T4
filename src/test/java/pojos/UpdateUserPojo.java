package pojos;

public class UpdateUserPojo {

    private String name;
    private String email;
    private String role;
    private boolean sendEmail;  // مهم

    // Constructor كامل
    public UpdateUserPojo(String name, String email, String role, boolean sendEmail) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.sendEmail = sendEmail;

    }

    // Getters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public boolean isSendEmail() { return sendEmail; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setRole(String role) { this.role = role; }
    public void setSendEmail(boolean sendEmail) { this.sendEmail = sendEmail; }
    }