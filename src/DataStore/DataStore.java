package DataStore;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import service.Course;
import service.Enrollment;
import service.GradeRecord;
import users.Admin;
import users.Instructor;
import users.StudentProfile;
import users.User;


public class DataStore {
    private final String USERS_FILE = "data/users.txt";
    private final String STUDENTS_FILE = "data/students.txt";
    private final String COURSES_FILE = "data/courses.txt";
    private final String ENROLLMENTS_FILE = "data/enrollments.txt";
    private final String GRADES_FILE = "data/grades.txt";
    
    public List<User> users;
    public List<StudentProfile> students;
    public List<Course> courses;
    public List<Enrollment> enrollments;
    public List<GradeRecord> grades;

//---------- initialization ----------
    public void initialize() {
        new File("data").mkdirs();

        users = new ArrayList<>();
        students = new ArrayList<>();
        courses = new ArrayList<>();
        enrollments = new ArrayList<>();
        grades = new ArrayList<>();
        
        if (users.isEmpty()) {
            Admin defaultAdmin = new Admin("admin", "admin", "Administrator", "");
            users.add(defaultAdmin);
            saveUsers();
        }
        
        loadStudents();
        loadUsers();
        loadCourses();
        loadEnrollments();
        loadGrades();
    }   
// ------------ Authentication -------------
    public User authenticate(String username, String password){
        return users.stream()
                .filter(u -> u.getUsername().equals(username)
                        && u.getPassword().equals(password))
                .findFirst().orElse(null);
    }
// --------------- find  --------------------
    public User findUser(String username){
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst().orElse(null);
    }
    
    public StudentProfile findStudentProfileByUsername(String username){
        return students.stream()
                .filter(s -> s.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
    
    public Course findCourse(String courseCode){
        return courses.stream()
                .filter(c -> c.getCourseCode().equals(courseCode))
                .findFirst()
                .orElse(null);
    }   
// --------- course -------    
    public List<Course> getCoursesByInstructor(String instructorUsername){
        return courses.stream()
                .filter(c -> c.getInstructorUsername().equals(instructorUsername))
                .collect(Collectors.toList());
    }   
// ------- enrollment -------------
    public int countEnrollmentForCourse(String courseCode){
        return (int) enrollments.stream()
                .filter(e -> e.getCourseCode().equals(courseCode))
                .count();
    }
    
    public boolean isStudentEnrolled(String studentUsername, String courseCode){
        return enrollments.stream()
                .anyMatch(e -> e.getStudentUsername().equals(studentUsername)
                && e.getCourseCode().equals(courseCode));
    }
    
    public List<Enrollment> getEnrollmentsByCourse(String courseCode){
        return enrollments.stream()
                .filter(e -> e.getCourseCode().equals(courseCode))
                .collect(Collectors.toList());
    }
    
    public List<Enrollment> getEnrollmentsByStudent(String studentUsername){
        return enrollments.stream()
                .filter(e -> e.getStudentUsername().equals(studentUsername))
                .collect(Collectors.toList());
    }
    
    public boolean removeEnrollment(String studentUsername, String courseCode){
        return enrollments.removeIf(e -> e.getStudentUsername().equals(studentUsername)
        && e.getCourseCode().equals(courseCode));
    }
// -------------- grades ----------------
    public GradeRecord findGrade(String studentUsername, String courseCode){
        return grades.stream()
                .filter(g -> g.getStudentUsername().equals(studentUsername)
                && g.getCourseCode().equals(courseCode))
                .findFirst()
                .orElse(null);
    }

    public List<GradeRecord> getGradeByStudent(String studentUsername){
        return grades.stream()
                .filter(g -> g.getStudentUsername().equals(studentUsername))
                .collect(Collectors.toList());
    }
    
    public void upsertGrade(String studentUsername, String courseCode, double midterm, double finalExam){
        GradeRecord existing = findGrade(studentUsername, courseCode);
        if (existing != null) {
            grades.remove(existing);
        }
        grades.add(new GradeRecord(studentUsername, courseCode, midterm, finalExam));
    }
    
    public double calculateGPA(String studentUsername) {
        List<GradeRecord> studentGrades = getGradeByStudent(studentUsername);
        if (studentGrades.isEmpty()) return 0.0;
        
        double totalPoints = 0.0;
        for (GradeRecord g: studentGrades){
            totalPoints += letterToGpaPoint(g.getLetterGrade());
        }
        return totalPoints / studentGrades.size();
    }
    
    private double letterToGpaPoint(String letter) {
        switch (letter) {
            case "AA": return 4.0;
            case "BA": return 3.5;
            case "BB": return 3.0;
            case "CB": return 2.5;
            case "CC": return 2.0;
            case "DC": return 1.5;
            case "DD": return 1.0;
            default:   return 0.0; 
        }
    } 
// ----------- save -------------
    public void saveUsers(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (User u : users) {
                if(!(u instanceof StudentProfile)){
                    bw.write(u.toFileString());
                    bw.newLine();
                }
            }
        }catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }
    
    public void saveStudents(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(STUDENTS_FILE))) {
            for (StudentProfile s : students){
                bw.write(s.toFileString());
                bw.newLine();
            }
        } catch(IOException e) {
            System.err.println("Error saving students: " + e.getMessage());
        }
    }
    
    public void saveCourses() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(COURSES_FILE))) {
            for (Course c : courses) {
                bw.write(c.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving courses: " + e.getMessage());
        }
    }
    
    public void saveEnrollments(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(ENROLLMENTS_FILE))) {
            for (Enrollment e : enrollments) {
                bw.write(e.toFileString());
                bw.newLine();
            }
        } catch(IOException e){
            System.err.println("Error saving Enrollments: " + e.getMessage());
        }
    }
   
    public void saveGrades() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(GRADES_FILE))) {
            for (GradeRecord g : grades) {
                bw.write(g.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving grades: " + e.getMessage());
        }
    }
// ------------ loading -------------- 
    public void loadUsers() {
        File f = new File(USERS_FILE);
        if (!f.exists()) return;
        
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null){
                line = line.trim();
                if (line.isEmpty()) continue;
                
                String[] parts = line.split("\\|",-1);
                if (parts.length < 4 ) continue;
                
                String role = parts[2];
                User user = null;
                switch(role){
                    case "ADMIN":
                        user = Admin.fromFileLine(line);
                        break;                             
                    case "INSTRUCTOR":
                        user = Instructor.fromFileLine(line);
                        break;                             
                }
                 if (user != null) users.add(user);
            }
        } catch (IOException e) {
            System.err.println("Error loading users: " + e.getMessage());
        }
    }  
    
    public void loadStudents() {
        File f = new File(STUDENTS_FILE);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                StudentProfile sp = StudentProfile.fromFileLine(line);
                if (sp != null) {
                    students.add(sp);
                    users.add(sp);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading students: " + e.getMessage());
        }
    }
    
    public void loadCourses() {
        File f = new File(COURSES_FILE);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                Course c = Course.fromFileLine(line);
                if (c != null) courses.add(c);
            }
        } catch (IOException e) {
            System.err.println("Error loading courses: " + e.getMessage());
        }
    }

    public void loadEnrollments() {
        File f = new File(ENROLLMENTS_FILE);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                Enrollment e = Enrollment.fromFileLine(line);
                if (e != null) enrollments.add(e);
            }
        } catch (IOException e) {
            System.err.println("Error loading enrollments: " + e.getMessage());
        }
    }

    public void loadGrades() {
        File f = new File(GRADES_FILE);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                GradeRecord g = GradeRecord.fromFileLine(line);
                if (g != null) grades.add(g);
            }
        } catch (IOException e) {
            System.err.println("Error loading grades: " + e.getMessage());
        }
    }
}
