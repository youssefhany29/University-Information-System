package source.users;

public class Student extends Person {

    private String studentId;
    private String department;   
    private int year;        

    public Student(String username, String password, String fullName, String studentId, String department, int year) {
        super(username, password, fullName, "STUDENT");
        this.studentId  = studentId;
        this.department = department;
        this.year = year;
    }

    public String getStudentId() {
        return studentId; 
    }
    public String getDepartment() {
        return department; 
    }
    public int getYear() {
        return year; 
    }

    public void setDepartment(String department) { 
        this.department = department; 
    }
    public void setYear(int year) {
        this.year = year;
    }
    
    @Override
    public String toFileLine() {
        return getUsername() + "|" + getPassword() + "|STUDENT|" + getFullName()
                + "|" + studentId + "|" + department + "|" + year;
    }

    public static Student fromFileLine(String line) {
        String[] person = line.split("\\|", -1);
        if (person.length < 7) 
            return null;
        try {
            int year = Integer.parseInt(person[6]);
            return new Student(person[0], person[1], person[3], person[4], person[5], year);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return getFullName() + " [" + studentId + "] – " + department + ", Year " + year;
    }
}
