package source.users;

public class User extends Person {
    
    private String referenceId;
    
    public User(String username, String password, String fullName, String role, String referenceId) {
        super(username, password, fullName, role);
        this.referenceId = referenceId;
    }
    
    public String getReferenceId() {
        return referenceId; 
    }

    public void setReferenceId(String ref) { 
        this.referenceId = ref;
    }

    @Override
    public String toFileLine() {
        return getUsername() + "|" + getPassword() + "|" +
               getRole() + "|" +
               getFullName() + "|" +
               referenceId;
    }    
    
    public static Person fromFileLine(String line) {

        if (line == null || line.trim().isEmpty())
            return null;

        String[] person = line.split("\\|", -1);
        if (person.length < 4)
            return null;

        String role = person[2].trim().trim().toUpperCase();
        
        switch (role) {

            case "ADMIN":
                return Admin.fromFileLine(line);
                
            case "INSTRUCTOR":
                return Instructor.fromFileLine(line);

            case "STUDENT":
                return Student.fromFileLine(line);
                
        }
        return null;
    }
}
