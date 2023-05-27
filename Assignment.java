import java.io.*;
import java.util.*;

class Student {
    private int id;
    private String name;
    private int totalMarks;

    

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", totalMarks=" + totalMarks +
                '}';
    }
}

class StudentGridSystem {
    private List<Student> studentList; 

    public StudentGridSystem() {
        studentList = new ArrayList<>();
    }

    public void loadStudentDetails(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
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

    public List<Student> getPaginatedStudentDetails(int pageNumber, int pageSize) {
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, studentList.size());

        if (startIndex >= studentList.size()) {
            return Collections.emptyList();
        }

        return studentList.subList(startIndex, endIndex);
    }

    public List<Student> filterStudentDetails(String filterCriteria) {
        List<Student> filteredList = new ArrayList<>();

        for (Student student : studentList) {
           
            if (student.getName().contains(filterCriteria)) {
                filteredList.add(student);
            }
        }

        return filteredList;
    }
}


public class Main {
    public static void main(String[] args) {
        StudentGridSystem gridSystem = new StudentGridSystem();

       
        gridSystem.loadStudentDetails("path/to/student/file.csv");

     
        int pageNumber = 1;
        int pageSize = 10;
        List<Student> studentsPage = gridSystem.getPaginatedStudentDetails(pageNumber, pageSize);
        System.out.println("Page " + pageNumber + " - Students:");
        for (Student student : studentsPage) {
            System.out.println(student);
        }

        
        String filterCriteria = "John";
        List<Student> filteredStudents = gridSystem.filterStudentDetails(filterCriteria);
        System.out.println("\nFiltered Students (Criteria: " + filterCriteria + "):");
        for (Student student : filteredStudents) {
            System.out.println(student);
        }

class Student {
    private int id;
    private String name;
    private int totalMarks;

   
}

class StudentGridSystem {
    private List<Student> studentList; 

    public StudentGridSystem() {
        studentList = new ArrayList<>();
    }

    public void loadStudentDetails(String filePath) {
       
    }

    public List<Student> getPaginatedStudentDetails(int pageNumber, int pageSize) {
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, studentList.size());
        if (startIndex >= endIndex) {
            return new ArrayList<>();
        }
        return studentList.subList(startIndex, endIndex);
    }

    public List<Student> filterStudentDetails() {
        
        List<Student> filteredStudents = new ArrayList<>();
        for (Student student : studentList) {
          
        }
        return filteredStudents;
    }

}

public class Main {
    public static void main(String[] args) {
        StudentGridSystem gridSystem = new StudentGridSystem();

        
        gridSystem.loadStudentDetails("path/to/student/file.csv");

        
        List<Student> studentsPage1 = gridSystem.getPaginatedStudentDetails(1, 10);

        List<Student> filteredStudents = gridSystem.filterStudentDetails(/* Add filter criteria */);

    }
}