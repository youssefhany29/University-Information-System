package users;


public class StudentProfile extends User{
    
    private String department;
    private int year;
    
    public StudentProfile(String username, String password, String fullName, String referenceId, String department, int year) {
        super(username, password, "STUDENT", fullName, referenceId);
        this.department = department;
        this.year = year;
    }
    
    public String getDepartment(){
        return department;
    }
    public int getYear(){
        return year;
    }

    @Override
    public String toFileString() {
        return getUsername() + "|" + getPassword() + "|" + "STUDENT" + "|" + getFullName() + "|" + getReferenceId() + "|" + getDepartment() + "|" + getYear();
    }    
    
    public static StudentProfile fromFileLine(String line){
        String[] u = line.split("\\|", -1);
        if (u.length < 7 ) return null;
        return new StudentProfile(u[0], u[1], u[3], u[4], u[5], Integer.parseInt(u[6]));
    }
}
