package source.models;

public class GradeRecord {
    
    private String studentUsername;
    private String courseCode;
    private double midterm;
    private double finalExam;
    
    public GradeRecord(String studentUsername, String courseCode, double midterm, double finalExam) {
        this.studentUsername = studentUsername;
        this.courseCode = courseCode;
        this.midterm = midterm;
        this.finalExam = finalExam;
    }
    
    public String getStudentUsername() {
        return studentUsername;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public double getMidterm() {
        return midterm;
    }

    public double getFinalExam() {
        return finalExam;
    }
    
    public double getTotal() {
        return midterm + finalExam;
    }

    public String toFileLine() {
        return studentUsername + "|" + courseCode + "|" + midterm + "|" + finalExam;
    }

    public static GradeRecord fromFileLine(String line) {
        if (line == null || line.trim().isEmpty()) return null;

        String[] p = line.split("\\|", -1);
        if (p.length < 4) return null;

        try {
            return new GradeRecord(
                p[0],
                p[1],
                Double.parseDouble(p[2]),
                Double.parseDouble(p[3])
            );
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return studentUsername + " | " + courseCode +
               " | Total: " + getTotal();
    }
}
