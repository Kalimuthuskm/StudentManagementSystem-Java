public class Student {
    // Private fields to store student details
    private int rollNo; // Unique identifier for each student
    private String name; // Student's name
    private String course; // Course enrolled by the student

    // Constructor to initialize student details
    Student(int rollNo, String name, String course) {
        this.rollNo = rollNo; // Assign roll number
        this.name = name; // Assign name
        this.course = course; // Assign course
    }

    // Getter method for roll number
    public int getRollNo() {
        return rollNo;
    }

    // Setter method for roll number (allows updating)
    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    // Getter method for name
    public String getName() {
        return name;
    }

    // Setter method for name (allows updating)
    public void setName(String name) {
        this.name = name;
    }

    // Getter method for course
    public String getCourse() {
        return course;
    }

    // Setter method for course (allows updating)
    public void setCourse(String course) {
        this.course = course;
    }

    // Overriding toString() method to display student details
    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNo + ", Course: " + course;
    }


}
