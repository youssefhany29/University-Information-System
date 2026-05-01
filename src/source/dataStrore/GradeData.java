package source.dataStrore;

import source.models.GradeRecord;
import java.io.*;
import java.util.*;

public class GradeData {

    private static final String FILE = "src/data/grades.txt";

    public static List<GradeRecord> loadGrades() {
        List<GradeRecord> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|");
                if (p.length >= 4) {
                    list.add(new GradeRecord(
                            p[0], p[1],
                            Double.parseDouble(p[2]),
                            Double.parseDouble(p[3])
                    ));
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public static void saveGrades(List<GradeRecord> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {

            for (GradeRecord g : list) {
                bw.write(g.toFileLine());
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addGrade(GradeRecord g) {
        List<GradeRecord> list = loadGrades();
        list.add(g);
        saveGrades(list);
    }

    public static void updateGrade(GradeRecord updated) {
        List<GradeRecord> list = loadGrades();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getStudentUsername().equals(updated.getStudentUsername())
                    && list.get(i).getCourseCode().equals(updated.getCourseCode())) {
                list.set(i, updated);
                break;
            }
        }

        saveGrades(list);
    }

    public static void deleteGrade(String student, String course) {
        List<GradeRecord> list = loadGrades();

        list.removeIf(g ->
                g.getStudentUsername().equals(student)
                        && g.getCourseCode().equals(course)
        );

        saveGrades(list);
    }
}
