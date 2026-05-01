package source.users;

public class Instructor extends Person{
    
    private String instructorId;
    private String faculty;        
    private String title; 

    public Instructor(String username, String password, String fullName, String instructorId, String faculty, String title) {
        super(username, password, fullName, "INSTRUCTOR");
        this.instructorId = instructorId;
        this.faculty = faculty;
        this.title = title;
    }
    
    public String getInstructorId() { 
        return instructorId;
    }
    public String getFaculty() {
        return faculty; 
    }
    public String getTitle() {
        return title; 
    }
 
    public void setFaculty(String faculty) {
        this.faculty = faculty; 
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toFileLine() {
        return getUsername() + "|" + getPassword() + "|INSTRUCTOR|" + getFullName() + "|" + instructorId + "|" + faculty + "|" + title;
    }
    
    public static Instructor fromFileLine(String line) {
        String[] person = line.split("\\|", -1);
        if (person.length < 7) return null;
        return new Instructor(person[0], person[1], person[3], person[4], person[5], person[6]);
    }
 
    @Override
    public String toString() {
        return title + " " + getFullName() + " (" + faculty + ")";
    }
}
