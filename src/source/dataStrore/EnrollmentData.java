package source.dataStrore;

import source.models.Enrollment;
import java.io.*;
import java.util.*;

public class EnrollmentData {

    private static final String FILE = "src/data/enrollments.txt";

    public static List<Enrollment> loadEnrollments() {
        List<Enrollment> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|");
                if (p.length >= 2) {
                    list.add(new Enrollment(p[0], p[1]));
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public static void saveEnrollments(List<Enrollment> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {

            for (Enrollment e : list) {
                bw.write(e.toFileLine());
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean isEnrolled(String student, String course) {
        return loadEnrollments().stream()
            .anyMatch(e -> e.equals(student + "|" + course));
    }
    
    public static void addEnrollment(Enrollment e) {
        List<Enrollment> list = loadEnrollments();
        list.add(e);
        saveEnrollments(list);
    }

    public static void deleteEnrollment(String student, String course) {
        List<Enrollment> list = loadEnrollments();
        list.removeIf(e ->
                e.getStudentUsername().equals(student) &&
                e.getCourseCode().equals(course)
        );
        saveEnrollments(list);
    }
}
