package source.models;

public class Course {
    private String courseCode;
    private String courseName;
    private int credit;
    private int quota;
    private String instructorUsername;
    
    public Course(String courseCode, String courseName, int credit, int quota, String instructorUsername){
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credit = credit;
        this.quota = quota;
        this.instructorUsername  = instructorUsername;
        
        if (credit <= 0) throw new IllegalArgumentException("Credit must be > 0");
        if (quota <= 0) throw new IllegalArgumentException("Quota must be > 0");
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
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public void setQuota(int quota) {
        this.quota = quota;
    }
    public void setCredit(int credit) {
        this.credit = credit;
    }    
    
    public String toFileLine(){
        return courseCode + "|" + courseName + "|" + credit + "|" + quota + "|" + instructorUsername;
    }
    
    public static Course fromFileLine(String line){
        if (line == null || line.trim().isEmpty()) return null;
        String[] course = line.split("\\|", -1);
        if (course.length < 5) return null;
        
        try {
            return new Course(course[0], course[1], Integer.parseInt(course[2]), Integer.parseInt(course[3]), course[4]);
        } catch(NumberFormatException e) {
            return null;
        }
    }
    
    @Override
    public String toString() {
        return courseCode + " - " + courseName + " (" + credit + " credits) | Instructor: " + instructorUsername;
    }
}
