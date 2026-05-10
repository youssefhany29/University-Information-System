package users;


public class Admin extends User {

    public Admin(String username, String password, String fullName, String referenceId) {
        super(username, password, "ADMIN", fullName, referenceId);
    }

    @Override
    public String toFileString() {
        return getUsername() + "|" + getPassword() + "|ADMIN|" + getFullName() + "|" + getReferenceId();
    }
    
    public static Admin fromFileLine(String line) {
        String[] u = line.split("\\|", -1);
        if (u.length < 5 ) return null;
       return new Admin(u[0], u[1], u[3], u[4]);
    }
}

