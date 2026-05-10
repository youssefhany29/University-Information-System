package users;


public abstract class User {
    private String username;
    private String password;
    private String role;
    private String fullName;
    private String referenceId;
   
   public User(String username, String password, String role, String fullName, String referenceId){
       this.username = username;
       this.password = password;
       this.role = role;
       this.fullName = fullName;
       this.referenceId = referenceId;
   } 
    public String getUsername() {
        return username; 
    }
    public String getPassword() { 
        return password; 
    }
    public String getFullName() { 
        return fullName; 
    }
    public String getRole() { 
        return role; 
    }
    public String getReferenceId() {
        return referenceId; 
    }

    public abstract String toFileString();
}
