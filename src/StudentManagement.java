import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagement {
    // List to store all student objects
    static ArrayList<Student> students = new ArrayList<>();
    // The filename to save and load student data
    private static final String FILE_NAME = "Student.csv";

    // Method to add a student to the list
    static void addStudent() {
        System.out.println("Add the Student Information ");
        Scanner scanner = new Scanner(System.in);
        try {
            // Roll number input
            System.out.println("Enter the Student Roll Number");
            int no = scanner.nextInt();
            if (no <= 0) {
                System.out.println("Roll number must be a positive integer.");
                return;
            }

            // Check if the roll number already exists
            for (Student student : students) {
                if (student.getRollNo() == no) {
                    System.out.println("A student with this roll number already exists.");
                    return;
                }
            }

            scanner.nextLine();  // Consume the newline
            // Name input
            System.out.println("Enter the Student Name :");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Name Cannot be empty");
                return;
            }

            // Course input
            System.out.println("Enter the Student Course :");
            String course = scanner.nextLine().trim();
            if (course.isEmpty()) {
                System.out.println("Course cannot be empty.");
                return;
            }

            // Add the student to the list
            students.add(new Student(no, name, course));
            System.out.println("Successfully Added Student Information");
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine();  // Clear invalid input
        }
    }

    // Method to view all students
    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found !");
            return;
        }
        // Display the student list in a formatted way
        System.out.println("----- Student List -----");
        System.out.printf("%-10s %-20s %-20s%n", "Roll No", "Name", "Course");
        for (Student student : students) {
            System.out.printf("%-10d %-20s %-20s%n", student.getRollNo(), student.getName(), student.getCourse());
        }
    }

    // Method to search a student by roll number
    static void searchStudent() {
        System.out.println("Enter the Roll Number to search:");
        Scanner scanner = new Scanner(System.in);
        int rollNo = scanner.nextInt();

        // Search for the student in the list
        for (Student student : students) {
            if (student.getRollNo() == rollNo) {
                System.out.println("Student Found !");
                System.out.printf("Roll No : %d, Name : %s, Course : %s%n", student.getRollNo(), student.getName(), student.getCourse());
                return;
            }
        }
        System.out.println("No student found with Roll Number " + rollNo);
    }

    // Method to delete a student by roll number
    static void deleteStudent() {
        if (students.isEmpty()) {
            System.out.println("No students found to delete");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the RollNumber :");
        try {
            int no = scanner.nextInt();
            // Find and remove the student
            for (Student student : students) {
                if (student.getRollNo() == no) {
                    students.remove(student);
                    System.out.println("Successfully Removed the student Information");
                    return;
                }
            }
            System.out.println("No student found with Roll Number " + no);
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid roll number.");
            scanner.nextLine();  // Clear invalid input
        }
    }

    // Method to update student information
    static void updateStudent() {
        if (students.isEmpty()) {
            System.out.println("No students found to update!");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Roll Number of the student to update:");
        try {
            int rollNo = scanner.nextInt();
            scanner.nextLine();  // Consume the newline
            // Find the student to update
            for (Student student : students) {
                if (student.getRollNo() == rollNo) {
                    System.out.println("Student Found!");
                    System.out.printf("Current Details: Roll No: %d, Name: %s, Course: %s%n", student.getRollNo(), student.getName(), student.getCourse());

                    // Ask if user wants to update the name
                    System.out.println("Do you want to update the Name? (yes/no)");
                    String updateName = scanner.nextLine().trim().toLowerCase();
                    if (updateName.equals("yes")) {
                        System.out.println("Enter the new Name:");
                        String newName = scanner.nextLine().trim();
                        if (!newName.isEmpty()) {
                            student.setName(newName);
                            System.out.println("Name updated successfully.");
                        } else {
                            System.out.println("Invalid name. Update cancelled for Name.");
                        }
                    }

                    // Ask if user wants to update the course
                    System.out.println("Do you want to update the Course? (yes/no)");
                    String updateCourse = scanner.nextLine().trim().toLowerCase();
                    if (updateCourse.equals("yes")) {
                        System.out.println("Enter the new Course:");
                        String newCourse = scanner.nextLine().trim();
                        if (!newCourse.isEmpty()) {
                            student.setCourse(newCourse);
                            System.out.println("Course updated successfully.");
                        } else {
                            System.out.println("Invalid course. Update cancelled for Course.");
                        }
                    }
                    System.out.println("Student details updated successfully!");
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine();  // Clear invalid input
        }
    }

    // Method to save student data to a file
    static void saveStudentsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student student : students) {
                writer.printf("%d,%s,%s%n", student.getRollNo(), student.getName(), student.getCourse());
            }
            System.out.println("Student data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving student data: " + e.getMessage());
        }
    }

    // Method to load student data from a file
    static void loadStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Skip blank lines
                if (line.trim().isEmpty()) continue;

                // Split and validate parts
                String[] parts = line.split(",");
                if (parts.length != 3) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                try {
                    int rollNo = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String course = parts[2].trim();

                    // Validate roll number
                    if (rollNo <= 0) {
                        System.out.println("Invalid roll number in line: " + line);
                        continue;
                    }

                    // Validate name and course
                    if (name.isEmpty() || course.isEmpty()) {
                        System.out.println("Name or course is empty in line: " + line);
                        continue;
                    }

                    // Add student to the list
                    students.add(new Student(rollNo, name, course));
                } catch (NumberFormatException e) {
                    System.out.println("Skipping line due to invalid roll number: " + line);
                }
            }
            System.out.println("Student data loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No existing data found. Starting fresh.");
        } catch (IOException e) {
            System.out.println("Error loading student data: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
