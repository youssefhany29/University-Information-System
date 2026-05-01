package source.dataStrore;

import source.users.*;
import java.io.*;
import java.util.*;

public class UserData {
    private static final String USER_FILE = "src/data/users.txt";

    public static List<Person> loadUsers() {

        List<Person> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {

            String line;
            while ((line = br.readLine()) != null) {
                Person p = User.fromFileLine(line); 
                if (p != null) {
                    list.add(p);
                } else {
                    System.out.println("Skipping bad line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public static void saveUsers(List<Person> users) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE))) {

            for (Person person : users) {
                bw.write(person.toFileLine());
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static Person getUser(String username) {
        return loadUsers().stream()
            .filter(u -> u.getUsername().equals(username))
            .findFirst()
            .orElse(null);
    }
    
    public static boolean userExists(String username) {
        return loadUsers().stream()
            .anyMatch(u -> u.getUsername().equals(username));
    }
    
    public static void addUser(Person user) {
        List<Person> users = loadUsers();
        boolean exists = users.stream()
            .anyMatch(u -> u.getUsername().equals(user.getUsername()));

        if (!exists) {
            users.add(user);
            saveUsers(users);
        } else {
            System.out.println("User already exists!");
        }   
    }

    public static void deleteUser(String username) {

        List<Person> users = loadUsers();
        users.removeIf(u ->
            u.getUsername().trim().equalsIgnoreCase(username.trim())
        );
        saveUsers(users);
    }

    public static void updateUser(Person updated) {
        List<Person> users = loadUsers();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(updated.getUsername())) {
                users.set(i, updated);
                break;
            }
        }
        saveUsers(users);
    }
}
