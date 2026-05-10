package service;


public class GradeRecord {
    private String studentUsername;
    private String courseCode;
    private double midterm;
    private double finalExam;
    
    public GradeRecord(String studentUsername, String courseCode, double midterm, double finalExam){
        this.studentUsername = studentUsername;
        this.courseCode = courseCode;
        this.midterm = midterm;
        this.finalExam = finalExam;
    }
    
    public String getStudentUsername(){
        return studentUsername;
    }
    public String getCourseCode(){
        return courseCode;
    }
    public double getMidterm(){
        return midterm;
    }
    public double getFinalExam(){
        return finalExam;
    }
    
    public double calculateAverage() {
        return midterm * 0.4 + finalExam * 0.6;
    }
    
    public String getLetterGrade() {
        double avg = calculateAverage();
        
        if (avg >= 90) return "AA";
        if (avg >= 80) return "BA";
        if (avg >= 70) return "BB";
        if (avg >= 60) return "CB";
        if (avg >= 50) return "CC";
        if (avg >= 40) return "DC";
        if (avg >= 30) return "DD";
        return "FF";
    }
    
    public String toFileString(){
        return studentUsername + "|" + courseCode + "|" + midterm + "|" + finalExam;
    }
    
    public static GradeRecord fromFileLine(String line){
        String[] g = line.split("\\|", -1);
        if (g.length < 4) return null;
        return new GradeRecord(g[0], g[1], Double.parseDouble(g[2]), Double.parseDouble(g[3]));
    }
}
