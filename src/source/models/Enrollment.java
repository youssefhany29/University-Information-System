package source.models;

public class Enrollment {
    private String studentUsername;
    private String courseCode;
    
    public Enrollment(String studentUsername, String courseCode) {
        this.studentUsername = studentUsername;
        this.courseCode = courseCode;
    }
    
    public String getStudentUsername() {
        return studentUsername;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String toFileLine() {
        return studentUsername +"|" + courseCode;
    }
    
    public static Enrollment fromFileLine(String line) {
        if (line == null || line.trim().isEmpty()) return null;
        
        String[] enrollment = line.split("\\|" , -1);
        if (enrollment.length < 2) return null;
        return new Enrollment(enrollment[0], enrollment[1]);
    }
     @Override
    public String toString() {
        return studentUsername + " -> " + courseCode;
    }
}
