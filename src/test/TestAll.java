package test;

import source.dataStrore.UserData;
import source.dataStrore.CourseData;
import source.dataStrore.EnrollmentData;
import source.dataStrore.GradeData;

import source.users.*;
import source.models.Course;
import source.models.Enrollment;
import source.models.GradeRecord;

public class TestAll {

    public static void main(String[] args) {

        System.out.println("=== FULL PROGRAM TEST START ===\n");

        // =========================
        // 1. TEST USERS
        // =========================
        System.out.println("--- USERS TEST ---");

        Person st = new Student(
                "test123",
                "pass",
                "Test User",
                "T100",
                "CS",
                1
        );

        UserData.addUser(st);
        System.out.println("User added.");

        System.out.println("Current Users:");
        System.out.println(UserData.loadUsers());

        UserData.deleteUser("test123");
        System.out.println("User deleted.\n");


        // =========================
        // 2. TEST COURSES
        // =========================
        System.out.println("--- COURSES TEST ---");

        Course c = new Course(
                "TST101",
                "Testing Course",
                3,
                40,
                "drtest"
        );

        CourseData.addCourse(c);
        System.out.println("Course added.");

        System.out.println("Current Courses:");
        System.out.println(CourseData.loadCourses());

        CourseData.deleteCourse("TST101");
        System.out.println("Course deleted.\n");


        // =========================
        // 3. TEST ENROLLMENT
        // =========================
        System.out.println("--- ENROLLMENT TEST ---");

        Enrollment e1 = new Enrollment("ali123", "CS101");

        EnrollmentData.addEnrollment(e1);
        System.out.println("Enrollment added.");

        System.out.println("Current Enrollments:");
        System.out.println(EnrollmentData.loadEnrollments());

        EnrollmentData.deleteEnrollment("ali123", "CS101");
        System.out.println("Enrollment deleted.\n");


        // =========================
        // 4. TEST GRADES
        // =========================
        System.out.println("--- GRADES TEST ---");

        GradeRecord g1 = new GradeRecord("ali123", "CS101", 20, 50);

        GradeData.addGrade(g1);
        System.out.println("Grade added.");

        System.out.println("Current Grades:");
        System.out.println(GradeData.loadGrades());

        GradeData.deleteGrade("ali123", "CS101");
        System.out.println("Grade deleted.\n");


        // =========================
        System.out.println("=== FULL PROGRAM TEST END ===");
    }
}
