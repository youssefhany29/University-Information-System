package test;

import source.dataStrore.*;
import source.models.*;
import source.users.*;

import java.util.List;

public class TestFiles {

    public static void main(String[] args) {

        System.out.println("=== FILE TEST START ===");

        // =========================
        // 1. USERS TEST
        // =========================
        System.out.println("\n--- USERS ---");

        Person s1 = new Student("ali123", "1234", "Ali Ahmed",
                "S100", "CS", 1);

        UserData.addUser(s1);

        List<Person> users = UserData.loadUsers();
        for (Person p : users) {
            System.out.println(p);
        }


        // =========================
        // 2. COURSE TEST
        // =========================
        System.out.println("\n--- COURSES ---");

        Course c = new Course("CS101", "OOP", 3, 50, "drjohn");

        CourseData.addCourse(c);

        List<Course> courses = CourseData.loadCourses();
        for (Course x : courses) {
            System.out.println(x);
        }


        // =========================
        // 3. ENROLLMENT TEST
        // =========================
        System.out.println("\n--- ENROLLMENTS ---");

        Enrollment e1 = new Enrollment("ali123", "CS101");

        EnrollmentData.addEnrollment(e1);

        List<Enrollment> enrollments = EnrollmentData.loadEnrollments();
        for (Enrollment e : enrollments) {
            System.out.println(e);
        }


        // =========================
        // 4. GRADES TEST
        // =========================
        System.out.println("\n--- GRADES ---");

        GradeRecord g1 = new GradeRecord("ali123", "CS101", 20, 50);

        GradeData.addGrade(g1);

        List<GradeRecord> grades = GradeData.loadGrades();
        for (GradeRecord g : grades) {
            System.out.println(g);
        }

        System.out.println("\n=== FILE TEST END ===");
    }
}
