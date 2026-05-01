package test;

import source.models.*;

public class TestModels {

    public static void main(String[] args) {

        System.out.println("=== MODELS TEST START ===");

        // =========================
        // 1. COURSE TEST
        // =========================
        System.out.println("\n--- COURSE TEST ---");

        Course c = new Course("CS101", "OOP", 3, 50, "drjohn");

        String courseLine = c.toFileLine();
        System.out.println("File Line: " + courseLine);

        Course c2 = Course.fromFileLine(courseLine);
        System.out.println("Object: " + c2);


        // =========================
        // 2. ENROLLMENT TEST
        // =========================
        System.out.println("\n--- ENROLLMENT TEST ---");

        Enrollment e = new Enrollment("ali123", "CS101");

        String enrollLine = e.toFileLine();
        System.out.println("File Line: " + enrollLine);

        Enrollment e2 = Enrollment.fromFileLine(enrollLine);
        System.out.println("Object: " + e2);


        // =========================
        // 3. GRADE TEST
        // =========================
        System.out.println("\n--- GRADE TEST ---");

        GradeRecord g = new GradeRecord("ali123", "CS101", 20, 50);

        String gradeLine = g.toFileLine();
        System.out.println("File Line: " + gradeLine);

        GradeRecord g2 = GradeRecord.fromFileLine(gradeLine);
        System.out.println("Object: " + g2);

        System.out.println("\n=== MODELS TEST END ===");
    }
}
