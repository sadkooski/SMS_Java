package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Student;

public class StudentManagerImpl implements StudentManager {
    private final String DATABASE_URL = "jdbc:sqlite:students.db";

    public StudentManagerImpl() {
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS students (" +
                "studentID TEXT PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "age INTEGER NOT NULL," +
                "grade REAL NOT NULL" +
                ");";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
                Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addStudent(Student student) {
        if (!student.getStudentID().matches("\\d+")) {
            throw new IllegalArgumentException("Student ID must be a number.");
        }
    
        String insertSQL = "INSERT INTO students (studentID, name, age, grade) VALUES (?, ?, ?, ?);";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
            PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, student.getStudentID());
            pstmt.setString(2, student.getName());
            pstmt.setInt(3, student.getAge());
            pstmt.setDouble(4, student.getGrade());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding student to database: " + e.getMessage());
        }
    }

    @Override
    public void removeStudent(String studentID) {
        String deleteSQL = "DELETE FROM students WHERE studentID = ?;";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
                PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setString(1, studentID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStudent(Student student) {
        String updateSQL = "UPDATE students SET name = ?, age = ?, grade = ? WHERE studentID = ?;";
    
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
            PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setDouble(3, student.getGrade());
            pstmt.setString(4, student.getStudentID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating student: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Student> displayAllStudents() {
        ArrayList<Student> students = new ArrayList<>();
        String selectSQL = "SELECT * FROM students;";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(selectSQL)) {
            while (rs.next()) {
                String id = rs.getString("studentID");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                double grade = rs.getDouble("grade");
                students.add(new Student(name, age, grade, id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public double calculateAverageGrade() {
        String avgSQL = "SELECT AVG(grade) AS averageGrade FROM students;";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(avgSQL)) {
            if (rs.next()) {
                return rs.getDouble("averageGrade");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public boolean studentExists(String studentID) {
        String checkSQL = "SELECT COUNT(*) AS count FROM students WHERE studentID = ?;";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
                PreparedStatement pstmt = conn.prepareStatement(checkSQL)) {
            pstmt.setString(1, studentID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("count") > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
