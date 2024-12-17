import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Load existing students from the file at the start of the program
        StudentManagement.loadStudentsFromFile();
        Scanner scanner = new Scanner(System.in);

        // Loop to display the menu and handle user input until they choose to exit
        while (true) {
            System.out.println("\n----- Student Management System -----");
            System.out.println("1. Add Student"); // Option to add a new student
            System.out.println("2. View Students"); // Option to display all students
            System.out.println("3. Search Student"); // Option to search for a student by roll number
            System.out.println("4. Delete Student"); // Option to delete a student record
            System.out.println("5. Update Student"); // Option to update student details
            System.out.println("6. Exit"); // Option to exit the program
            System.out.println("Enter your choice:");

            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // Add a new student and save the updated list to the file
                        StudentManagement.addStudent();
                        StudentManagement.saveStudentsToFile();
                        break;

                    case 2:
                        // Display the list of all students
                        StudentManagement.viewStudents();
                        break;

                    case 3:
                        // Search for a student by roll number
                        StudentManagement.searchStudent();
                        break;

                    case 4:
                        // Delete a student and save the updated list to the file
                        StudentManagement.deleteStudent();
                        StudentManagement.saveStudentsToFile();
                        break;

                    case 5:
                        // Update student details and save the updated list to the file
                        StudentManagement.updateStudent();
                        StudentManagement.saveStudentsToFile();
                        break;

                    case 6:
                        // Exit the program after saving data to the file
                        System.out.println("Saving data...");
                        StudentManagement.saveStudentsToFile();
                        System.out.println("Exit... Thank you for using the system!");
                        return; // Terminates the loop and program

                    default:
                        // Handles invalid menu options
                        System.out.println("Invalid choice. Please select a valid option from 1 to 6.");
                }
            } catch (Exception e) {
                // Handles non-integer inputs gracefully
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                scanner.nextLine(); // Clear invalid input to prevent an infinite loop
            }
        }
    }
}
