package test;

import source.dataStrore.UserData;
import source.users.*;

public class TestUsers {

    public static void main(String[] args) {

        System.out.println("=== USER TEST START ===");

        // ======================
        // 1. CREATE USERS
        // ======================
        Person admin = new Admin("admin1", "1234", "System Admin");

        Person student = new Student(
                "ali123",
                "1111",
                "Ali Ahmed",
                "S100",
                "CS",
                1
        );

        Person instructor = new Instructor(
                "drjohn",
                "9999",
                "John Doe",
                "I10",
                "Engineering",
                "Dr."
        );

        // ======================
        // 2. ADD USERS
        // ======================
        UserData.addUser(admin);
        UserData.addUser(student);
        UserData.addUser(instructor);

        System.out.println("Users added ✔");

        // ======================
        // 3. LOAD USERS
        // ======================
        System.out.println("\n--- ALL USERS ---");
        for (Person p : UserData.loadUsers()) {
            System.out.println(p);
        }

        // ======================
        // 4. DELETE USER
        // ======================
        UserData.deleteUser("ali123");
        System.out.println("\nUser ali123 deleted ✔");

        // ======================
        // 5. FINAL CHECK
        // ======================
        System.out.println("\n--- AFTER DELETE ---");
        for (Person p : UserData.loadUsers()) {
            System.out.println(p);
        }

        System.out.println("\n=== TEST FINISHED ===");
    }
}
