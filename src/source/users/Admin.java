package source.users;


public class Admin extends Person{
    
    public Admin(String username, String password, String fullName) {
        super(username, password, fullName, "ADMIN");
    }
    
    @Override
    public String toFileLine() {
        return getUsername() + "|" + getPassword() + "|ADMIN|" + getFullName();
    }
    
    public static Admin fromFileLine(String line) {
        String [] person = line.split("\\|", -1);
        if (person.length < 4) return null;
        return new Admin(person[0].trim(), person[1].trim(), person[3].trim());
    }
    @Override
    public String toString() {
        return getFullName() + " (Admin) ";
    }

}
