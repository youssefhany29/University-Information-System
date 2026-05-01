package source.users;

public abstract class Person {
    private String username;
    private String password;
    private String fullName;
    private String role;      

    public Person(String username, String password, String fullName, String role) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }


    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
    
    public String getFullName() {
        return this.fullName;
    }
    
    public String getRole() {
        return this.role;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public abstract String toFileLine();
    
    @Override
    public String toString() {
        return fullName + "[" + role + "] (" + username + ")";
    }
}
