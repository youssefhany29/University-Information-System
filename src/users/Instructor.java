package users;


public class Instructor extends User {

    public Instructor(String username, String password, String fullName, String referenceId) {
        super(username, password, "INSTRUCTOR", fullName, referenceId);
    }

    @Override
    public String toFileString() {
    return getUsername() + "|" + getPassword() + "|INSTRUCTOR|" + getFullName() + "|" + getReferenceId();
    }    
    public static Instructor fromFileLine(String line) {
        String[] u = line.split("\\|", -1);
        if (u.length < 5) return null;

        return new Instructor(u[0], u[1], u[3], u[4]);
    }
}
