package source.dataStrore;

import java.io.*;
import java.util.*;
import static source.dataStrore.UserData.loadUsers;
import source.users.Student;


public class StudentData {
    public static final String Student_Data = "src/data/students.txt";
    
    public static List<Student> loadStudents() {
        List<Student> list = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(Student_Data))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                Student s = Student.fromFileLine(line);
                if (s != null) list.add(s);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());            
        }
        return list;
    }
    
    public  static void saveStudents(List<Student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Student_Data))) {

            for (Student s : students) {
                bw.write(s.toFileLine());
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    } 
    
    public static boolean studentExists(String username) {
        return loadUsers().stream()
        .anyMatch(u -> u instanceof Student && u.getUsername().equals(username));
    }
    
    public static void addStudent(Student newStudent) {
        List<Student> students = loadStudents(); 
        students.add(newStudent); 
        saveStudents(students); 
    }

    public static void deleteStudent(String studentId) {
        List<Student> students = loadStudents();
        students.removeIf(s -> s.getStudentId().equals(studentId));
        saveStudents(students);
    }
    
    public static void updateStudent(Student updatedStudent) {
        List<Student> students = loadStudents();

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(updatedStudent.getStudentId())) {
                students.set(i, updatedStudent);
                break;
            }
        }
        saveStudents(students);
    }      
    
    
}
