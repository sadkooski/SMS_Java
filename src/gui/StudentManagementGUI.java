package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import manager.StudentManager;
import manager.StudentManagerImpl;
import model.Student;

public class StudentManagementGUI {
    private JFrame frame; // Główne okno aplikacji
    private JTextField idField, nameField, ageField, gradeField; // Pola wejściowe dla danych studenta
    private JTextArea outputArea; // Obszar wyjściowy dla komunikatów i listy studentów
    private StudentManager manager; // Logika biznesowa do zarządzania studentami

    public StudentManagementGUI() {
        manager = new StudentManagerImpl(); // Inicjalizacja menedżera studentów
        initialize(); // Inicjalizacja GUI
    }

    private void initialize() {
        frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Zamykanie aplikacji po zamknięciu okna
        frame.setSize(820, 440);
        frame.setLayout(new BorderLayout()); // Użycie układu BorderLayout

        // Panel wejściowy dla etykiet i pól tekstowych
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 4, 10, 10)); // 2 wiersze, 4 kolumny, odstępy między elementami

        // Ustawienia dla etykiet
        java.awt.Font labelFont = new java.awt.Font("SansSerif", java.awt.Font.BOLD, 14);

        // Wiersz 1: Etykiety
        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setHorizontalAlignment(SwingConstants.CENTER);
        idLabel.setFont(labelFont);
        inputPanel.add(idLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setFont(labelFont);
        inputPanel.add(nameLabel);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ageLabel.setFont(labelFont);
        inputPanel.add(ageLabel);

        JLabel gradeLabel = new JLabel("Grade:");
        gradeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gradeLabel.setFont(labelFont);
        inputPanel.add(gradeLabel);

        // Wiersz 2: Pola tekstowe
        idField = new JTextField(); // Pole tekstowe dla ID studenta
        inputPanel.add(idField);

        nameField = new JTextField(); // Pole tekstowe dla imienia
        inputPanel.add(nameField);

        ageField = new JTextField(); // Pole tekstowe dla wieku
        inputPanel.add(ageField);

        gradeField = new JTextField(); // Pole tekstowe dla oceny
        inputPanel.add(gradeField);

        // Dodanie panelu wejściowego do ramki
        frame.add(inputPanel, BorderLayout.NORTH);

        // Panel przycisków
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Wyśrodkowany układ z odstępami

        // Wymiary przycisków
        java.awt.Dimension buttonSize = new java.awt.Dimension(150, 30);

        // Dodanie przycisków
        JButton addButton = new JButton("Add Student");
        addButton.setPreferredSize(buttonSize);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent(); // Obsługa przycisku "Add Student"
            }
        });
        buttonPanel.add(addButton);

        JButton removeButton = new JButton("Remove Student");
        removeButton.setPreferredSize(buttonSize);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStudent(); // Obsługa przycisku "Remove Student"
            }
        });
        buttonPanel.add(removeButton);

        JButton displayButton = new JButton("Display All Students");
        displayButton.setPreferredSize(buttonSize);
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAllStudents(); // Obsługa przycisku "Display All Students"
            }
        });
        buttonPanel.add(displayButton);

        JButton averageButton = new JButton("Calculate Average");
        averageButton.setPreferredSize(buttonSize);
        averageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAverageGrade(); // Obsługa przycisku "Calculate Average"
            }
        });
        buttonPanel.add(averageButton);

        JButton updateButton = new JButton("Update Student");
        updateButton.setPreferredSize(buttonSize);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStudent(); // Obsługa przycisku "Update Student"
            }
        });
        buttonPanel.add(updateButton);

        // Dodanie panelu przycisków do ramki
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Tworzenie obszaru tekstowego do wyświetlania danych
        outputArea = new JTextArea();
        outputArea.setEditable(false); // Pole tylko do odczytu
        outputArea.setPreferredSize(new Dimension(580, 300));
        JScrollPane scrollPane = new JScrollPane(outputArea); // Dodanie przewijania

        // Dodanie obszaru tekstowego do ramki
        frame.add(scrollPane, BorderLayout.SOUTH);

        // Ustawienie ramki jako widocznej
        frame.setVisible(true);
    }
    
    private String validateFields() {
        // Metoda walidująca, czy wszystkie pola wejściowe są wypełnione
        if (idField.getText().trim().isEmpty()) return "Student ID";
        if (nameField.getText().trim().isEmpty()) return "Name";
        if (ageField.getText().trim().isEmpty()) return "Age";
        if (gradeField.getText().trim().isEmpty()) return "Grade";
        return null; // Wszystkie pola są prawidłowe
    }

    private void addStudent() {
        // Walidacja wypełnienia pól
        String missingField = validateFields();
        if (missingField != null) {
            outputArea.setText("Error: The '" + missingField + "' field must be filled.");
            clearInputFields(); // Czyszczenie pól
            return;
        }
    
        try {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim()); // Parsowanie wieku
            double grade = Double.parseDouble(gradeField.getText().trim()); // Parsowanie oceny
    
            // Walidacja zakresu oceny
            if (grade < 0.0 || grade > 100.0) {
                outputArea.setText("Error: Grade must be a number between 0.0 and 100.0.");
                clearInputFields(); // Czyszczenie pól
                return;
            }
    
            // Dodanie studenta do bazy
            manager.addStudent(new Student(name, age, grade, id));
            outputArea.setText("Student added successfully!");
        } catch (NumberFormatException e) {
            outputArea.setText("Error: Age must be an integer and Grade must be a number.");
        } catch (Exception e) {
            outputArea.setText("Error adding student: " + e.getMessage());
        } finally {
            clearInputFields(); // Czyszczenie pól po każdej akcji
        }
    }

    private void removeStudent() {
        // Metoda obsługująca usuwanie studenta
        String studentId = idField.getText().trim();
        if (studentId.isEmpty()) {
            outputArea.setText("Error: The 'Student ID' field must be filled to remove a student.");
            return;
        }
        
        try {
            if (!manager.studentExists(studentId)) {
                outputArea.setText("Error: No student exists with ID '" + studentId + "'. Use 'Display All Students' to view valid IDs.");
                return;
            }
            manager.removeStudent(studentId); // Usunięcie studenta
            outputArea.setText("Student with ID '" + studentId + "' removed successfully!");
        } catch (Exception e) {
            outputArea.setText("Error removing student: " + e.getMessage());
        }
    }

    private void displayAllStudents() {
        // Metoda obsługująca wyświetlanie wszystkich studentów
        ArrayList<Student> students = manager.displayAllStudents();
        StringBuilder sb = new StringBuilder();
        for (Student student : students) {
            sb.append(student.getStudentID()).append(", ")
                .append(student.getName()).append(", ")
                .append(student.getAge()).append(", ")
                .append(student.getGrade()).append("\n");
        }
        outputArea.setText(sb.toString());
    }

    private void calculateAverageGrade() {
        // Metoda obsługująca obliczanie średniej ocen
        double average = manager.calculateAverageGrade();
        outputArea.setText("Average Grade: " + average);
    }

    private void clearInputFields() {
        idField.setText(""); // Czyszczenie pola ID
        nameField.setText(""); // Czyszczenie pola imienia
        ageField.setText(""); // Czyszczenie pola wieku
        gradeField.setText(""); // Czyszczenie pola oceny
    }

    private void updateStudent() {
        // Pobierz ID studenta
        String studentId = idField.getText().trim();
        if (studentId.isEmpty()) {
            outputArea.setText("Error: The 'Student ID' field must be filled to update a student.");
            return;
        }
    
        try {
            // Sprawdź, czy student istnieje
            if (!manager.studentExists(studentId)) {
                outputArea.setText("Error: No student exists with ID '" + studentId + "'. Use 'Display All Students' to view valid IDs.");
                return;
            }
    
            // Walidacja pól formularza
            String missingField = validateFields();
            if (missingField != null) {
                outputArea.setText("Error: The '" + missingField + "' field must be filled.");
                return;
            }
    
            // Pobierz nowe dane
            String newName = nameField.getText();
            int newAge = Integer.parseInt(ageField.getText()); // Parsowanie wieku
            double newGrade = Double.parseDouble(gradeField.getText()); // Parsowanie oceny
    
            // Zaktualizuj studenta
            manager.updateStudent(new Student(newName, newAge, newGrade, studentId));
    
            // Wyświetl komunikat sukcesu
            outputArea.setText("Student with ID '" + studentId + "' updated successfully!\n" +
                "New Data: " + studentId + ", " + newName + ", " + newAge + ", " + newGrade);
    
            // Opcjonalne: Odśwież listę studentów po aktualizacji
            displayAllStudents();
        } catch (NumberFormatException e) {
            outputArea.setText("Error: Age must be an integer and Grade must be a number.");
        } catch (Exception e) {
            outputArea.setText("Error updating student: " + e.getMessage());
        }
    }
}