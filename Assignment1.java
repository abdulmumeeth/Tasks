import java.io.*;
import java.util.*;

// Student class representing the student details
class Student {
    private int id;
    private String name;
    private int totalMarks;

    // Constructor, getters, and setters for Student class
    // ...

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", totalMarks=" + totalMarks +
                '}';
    }
}

// Backend API class responsible for loading student details and implementing filtering
class StudentGridSystem {
    private List<Student> studentList; // Holds the loaded student data from file

    // Constructor for StudentGridSystem class
    public StudentGridSystem() {
        studentList = new ArrayList<>();
    }

    // Method to load student details from a file
    public void loadStudentDetails(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Assuming CSV format: id,name,totalMarks
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int totalMarks = Integer.parseInt(parts[2]);

                Student student = new Student();
                student.setId(id);
                student.setName(name);
                student.setTotalMarks(totalMarks);

                studentList.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve student details in a paginated manner
    public List<Student> getPaginatedStudentDetails(int pageNumber, int pageSize) {
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, studentList.size());

        if (startIndex >= studentList.size()) {
            return Collections.emptyList();
        }

        return studentList.subList(startIndex, endIndex);
    }

    // Method to filter student details based on the provided criteria
    public List<Student> filterStudentDetails(String filterCriteria) {
        List<Student> filteredList = new ArrayList<>();

        // Implement the filtering logic based on the provided criteria
        for (Student student : studentList) {
            // Assuming filtering based on student name
            if (student.getName().contains(filterCriteria)) {
                filteredList.add(student);
            }
        }

        return filteredList;
    }
}

// Main class to test the program
public class Main {
    public static void main(String[] args) {
        // Create an instance of the StudentGridSystem
        StudentGridSystem gridSystem = new StudentGridSystem();

        // Load student details from a file
        gridSystem.loadStudentDetails("path/to/student/file.csv");

        // Retrieve paginated student details
        int pageNumber = 1;
        int pageSize = 10;
        List<Student> studentsPage = gridSystem.getPaginatedStudentDetails(pageNumber, pageSize);
        System.out.println("Page " + pageNumber + " - Students:");
        for (Student student : studentsPage) {
            System.out.println(student);
        }

        // Filter student details based on criteria
        String filterCriteria = "John";
        List<Student> filteredStudents = gridSystem.filterStudentDetails(filterCriteria);
        System.out.println("\nFiltered Students (Criteria: " + filterCriteria + "):");
        for (Student student : filteredStudents) {
            System.out.println(student);
        }

        // Additional code for implementing the server-side APIs and integrating with the UI