package service;

public class Enrollment {
    private String studentUsername;
    private String courseCode;
    
    public Enrollment(String studentUsername, String courseCode) {
        this.studentUsername = studentUsername;
        this.courseCode = courseCode;
    }
    
    public String getStudentUsername(){
        return studentUsername;
    }
    public String getCourseCode(){
        return courseCode;
    }
    
    public String toFileString(){
        return getStudentUsername() + "|" + getCourseCode();
    }
    public static Enrollment fromFileLine(String line){
        String[] e = line.split("\\|", -1);
        if (e.length < 2) return null;
        return new Enrollment(e[0], e[1]);
    }
}
