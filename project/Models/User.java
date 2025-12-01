package Models;

/**
 * Represents a user in the Point of Sale system.
 * Users can have different roles: ADMIN or CLIENT.
 */
public class User {
    
    /**
     * Enumeration of user roles in the system.
     */
    public enum Role {
        ADMIN,   // Administrator with full access
        CLIENT   // Client with limited access
    }

    private int id;
    private String username;
    private String password;
    private String fullName;
    private Role role;
    private String email;

    /**
     * Creates a new User with the specified details.
     * 
     * @param id       Unique identifier for the user
     * @param username Username for login
     * @param password Password for authentication
     * @param fullName Full name of the user
     * @param role     Role of the user (ADMIN or CLIENT)
     * @param email    Email address of the user
     */
    public User(int id, String username, String password, String fullName, Role role, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.email = email;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Checks if the user is an administrator.
     * 
     * @return true if user has ADMIN role
     */
    public boolean isAdmin() {
        return role == Role.ADMIN;
    }

    /**
     * Validates the provided password against the user's password.
     * 
     * @param inputPassword Password to validate
     * @return true if passwords match
     */
    public boolean validatePassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    @Override
    public String toString() {
        return String.format("User{id=%d, username='%s', fullName='%s', role=%s}", id, username, fullName, role);
    }
}
