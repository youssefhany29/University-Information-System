package source.dataStrore;

import source.models.Course;
import java.io.*;
import java.util.*;

public class CourseData {
    private static final String Course_Data = "src/data/courses.txt";

    public static List<Course> loadCourses() {
        List<Course> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(Course_Data))) {
            String line;

            while ((line = br.readLine()) != null) {
                Course c = Course.fromFileLine(line);
                if (c != null) list.add(c);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public static void saveCourses(List<Course> courses) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Course_Data))) {

            for (Course c : courses) {
                bw.write(c.toFileLine());
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static boolean courseExists(String courseCode) {
        return loadCourses().stream()
            .anyMatch(c -> c.getCourseCode().equals(courseCode));
    }
    
    public static void addCourse(Course course) {
        List<Course> courses = loadCourses();
        courses.add(course);
        saveCourses(courses);
    }

    public static void deleteCourse(String code) {
        List<Course> courses = loadCourses();

        courses.removeIf(c -> c.getCourseCode().equals(code));

        saveCourses(courses);
    }

    public static void updateCourse(Course updated) {
        List<Course> courses = loadCourses();

        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseCode().equals(updated.getCourseCode())) {
                courses.set(i, updated);
                break;
            }
        }

        saveCourses(courses);
    }
}
