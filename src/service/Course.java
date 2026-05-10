package service;

public class Course {

    private String courseCode;
    private String courseName;
    private int credit;
    private int quota;
    private String instructorUsername;

    public Course(String courseCode, String courseName, int credit, int quota, String instructorUsername) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credit = credit;
        this.quota = quota;
        this.instructorUsername = instructorUsername;
    }

    public String getCourseCode(){
        return courseCode; 
    }
    public String getCourseName(){
        return courseName; 
    }
    public int getCredit(){ 
        return credit; 
    }
    public int getQuota(){
        return quota;
    }
    public String getInstructorUsername(){
        return instructorUsername;
    }

    public String toFileString() {
        return courseCode + "|" + courseName + "|" + credit + "|" + quota + "|" + instructorUsername;
    }

    public static Course fromFileLine(String line) {
        String[] c = line.split("\\|", -1);
        return new Course(c[0], c[1],
                Integer.parseInt(c[2]),
                Integer.parseInt(c[3]),
                c[4]
        );
    }
}
