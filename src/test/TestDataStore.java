package test;

import source.dataStrore.CourseData;
import source.dataStrore.EnrollmentData;
import source.dataStrore.GradeData;
import source.dataStrore.UserData;
import source.users.*;
import source.models.Course;
import source.models.Enrollment;
import source.models.GradeRecord;

public class TestDataStore {

    public static void main(String[] args) {

        // =========================
        // 1. TEST USERS
        // =========================
        System.out.println("=== USERS TEST ===");

        Person student = new Student(
                "ali123",
                "1234",
                "Ali Ahmed",
                "S100",
                "CS",
                1
        );

        UserData.addUser(student);
        System.out.println("User added");

        System.out.println(UserData.loadUsers());

        UserData.deleteUser("ali123");
        System.out.println("User deleted");


        // =========================
        // 2. TEST COURSES
        // =========================
        System.out.println("\n=== COURSES TEST ===");

        Course c = new Course(
                "CS101",
                "OOP",
                3,
                50,
                "drjohn"
        );

        CourseData.addCourse(c);
        System.out.println("Course added");

        System.out.println(CourseData.loadCourses());

        CourseData.deleteCourse("CS101");
        System.out.println("Course deleted");


        // =========================
        // 3. TEST ENROLLMENT
        // =========================
        System.out.println("\n=== ENROLLMENT TEST ===");

        Enrollment e = new Enrollment("ali123", "CS101");
        EnrollmentData.addEnrollment(e);        
        System.out.println("Enrollment added");

        System.out.println(EnrollmentData.loadEnrollments());

        EnrollmentData.deleteEnrollment("ali123", "CS101");
        System.out.println("Enrollment deleted");


        // =========================
        // 4. TEST GRADES
        // =========================
        System.out.println("\n=== GRADES TEST ===");

        GradeRecord g = new GradeRecord("ali123", "CS101", 20, 50);
        GradeData.addGrade(g);
        System.out.println("Grade added");

        System.out.println(GradeData.loadGrades());

        GradeData.deleteGrade("ali123", "CS101");
        System.out.println("Grade deleted");
        
        
    }
}
