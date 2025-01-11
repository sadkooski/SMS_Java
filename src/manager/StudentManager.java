package manager;

import java.util.ArrayList;

import model.Student;

public interface StudentManager {
    boolean studentExists(String studentID);
    void addStudent(Student student);
    void removeStudent(String studentID);
    void updateStudent(Student student);
    ArrayList<Student> displayAllStudents();
    double calculateAverageGrade();
}
