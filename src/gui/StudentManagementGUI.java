// package gui;

// import java.awt.BorderLayout;
// import java.awt.GridLayout;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.util.ArrayList;

// import javax.swing.JButton;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JPanel;
// import javax.swing.JScrollPane;
// import javax.swing.JTextArea;
// import javax.swing.JTextField;

// import manager.StudentManager;
// import manager.StudentManagerImpl;
// import model.Student;

// public class StudentManagementGUI {
//     private JFrame frame;
//     private JTextField idField, nameField, ageField, gradeField;
//     private JTextArea outputArea;
//     private StudentManager manager;

//     public StudentManagementGUI() {
//         manager = new StudentManagerImpl();
//         initialize();
//     }

//     private void initialize() {
//         frame = new JFrame("Student Management System");
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setSize(1000, 700);
//         frame.setLayout(new BorderLayout());

//         JPanel inputPanel = new JPanel();
//         inputPanel.setLayout(new GridLayout(4, 2));

//         inputPanel.add(new JLabel("Student ID:"));
//         idField = new JTextField();
//         inputPanel.add(idField);

//         inputPanel.add(new JLabel("Name:"));
//         nameField = new JTextField();
//         inputPanel.add(nameField);

//         inputPanel.add(new JLabel("Age:"));
//         ageField = new JTextField();
//         inputPanel.add(ageField);

//         inputPanel.add(new JLabel("Grade:"));
//         gradeField = new JTextField();
//         inputPanel.add(gradeField);

//         JButton addButton = new JButton("Add Student");
//         addButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 addStudent();
//             }
//         });
//         inputPanel.add(addButton);

//         JButton removeButton = new JButton("Remove Student");
//         removeButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 removeStudent();
//             }
//         });
//         inputPanel.add(removeButton);

//         JButton displayButton = new JButton("Display All Students");
//         displayButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 displayAllStudents();
//             }
//         });
//         inputPanel.add(displayButton);

//         JButton averageButton = new JButton("Calculate Average");
//         averageButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 calculateAverageGrade();
//             }
//         });
//         inputPanel.add(averageButton);

//         frame.add(inputPanel, BorderLayout.NORTH);

//         outputArea = new JTextArea();
//         outputArea.setEditable(false);
//         frame.add(new JScrollPane(outputArea), BorderLayout.CENTER);

//         frame.setVisible(true);
//     }

//     private void addStudent() {
//         try {
//             String id = idField.getText();
//             String name = nameField.getText();
//             int age = Integer.parseInt(ageField.getText());
//             double grade = Double.parseDouble(gradeField.getText());
//             manager.addStudent(new Student(name, age, grade, id));
//             outputArea.setText("Student added successfully!");
//         } catch (Exception e) {
//             outputArea.setText("Error adding student: " + e.getMessage());
//         }
//     }

//     private void removeStudent() {
//         try {
//             String id = idField.getText();
//             manager.removeStudent(id);
//             outputArea.setText("Student removed successfully!");
//         } catch (Exception e) {
//             outputArea.setText("Error removing student: " + e.getMessage());
//         }
//     }

//     private void displayAllStudents() {
//         ArrayList<Student> students = manager.displayAllStudents();
//         StringBuilder sb = new StringBuilder();
//         for (Student student : students) {
//             sb.append(student.getStudentID()).append(", ")
//                 .append(student.getName()).append(", ")
//                 .append(student.getAge()).append(", ")
//                 .append(student.getGrade()).append("\n");
//         }
//         outputArea.setText(sb.toString());
//     }

//     private void calculateAverageGrade() {
//         double average = manager.calculateAverageGrade();
//         outputArea.setText("Average Grade: " + average);
//     }

//     public static void main(String[] args) {
//         new StudentManagementGUI();
//     }
// }


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
    private JFrame frame;
    private JTextField idField, nameField, ageField, gradeField;
    private JTextArea outputArea;
    private StudentManager manager;

    public StudentManagementGUI() {
        manager = new StudentManagerImpl();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 440);
        frame.setLayout(new BorderLayout());

        // Create a parent panel for labels and inputs
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 4, 10, 10)); // 2 rows, 4 columns, with gaps

        // Custom font for labels
        java.awt.Font labelFont = new java.awt.Font("SansSerif", java.awt.Font.BOLD, 14);

        // Row 1: Labels
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

        // Row 2: Input fields
        idField = new JTextField();
        inputPanel.add(idField);

        nameField = new JTextField();
        inputPanel.add(nameField);

        ageField = new JTextField();
        inputPanel.add(ageField);

        gradeField = new JTextField();
        inputPanel.add(gradeField);

        // Add the inputPanel to the frame
        frame.add(inputPanel, BorderLayout.NORTH);

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Centered with gaps

        // Define button dimensions
        java.awt.Dimension buttonSize = new java.awt.Dimension(150, 30); // Fixed height

        // Add buttons to the panel
        JButton addButton = new JButton("Add Student");
        addButton.setPreferredSize(buttonSize);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        buttonPanel.add(addButton);

        JButton removeButton = new JButton("Remove Student");
        removeButton.setPreferredSize(buttonSize);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });
        buttonPanel.add(removeButton);

        JButton displayButton = new JButton("Display All Students");
        displayButton.setPreferredSize(buttonSize);
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAllStudents();
            }
        });
        buttonPanel.add(displayButton);

        JButton averageButton = new JButton("Calculate Average");
        averageButton.setPreferredSize(buttonSize);
        averageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAverageGrade();
            }
        });
        buttonPanel.add(averageButton);

        // Add the button panel to the frame
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Create text area for output
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setPreferredSize(new Dimension(580, 300)); // Set a fixed height for the output area
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Add the scrollable output area to the frame
        frame.add(scrollPane, BorderLayout.SOUTH);

        // Make the frame visible
        frame.setVisible(true);
    }
    
    private String validateFields() {
        if (idField.getText().trim().isEmpty()) return "Student ID";
        if (nameField.getText().trim().isEmpty()) return "Name";
        if (ageField.getText().trim().isEmpty()) return "Age";
        if (gradeField.getText().trim().isEmpty()) return "Grade";
        return null; // All fields are valid
    }

    private void addStudent() {
        String missingField = validateFields();
        if (missingField != null) {
            outputArea.setText("Error: The '" + missingField + "' field must be filled.");
            return;
        }
    
        try {
            String id = idField.getText();
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            double grade = Double.parseDouble(gradeField.getText());
            manager.addStudent(new Student(name, age, grade, id));
            outputArea.setText("Student added successfully!");
        } catch (NumberFormatException e) {
            outputArea.setText("Error: Age must be an integer and Grade must be a number.");
        } catch (Exception e) {
            outputArea.setText("Error adding student: " + e.getMessage());
        }
    }

    private void removeStudent() {
        String studentId = idField.getText().trim();
        
        // Walidacja: ID musi być wypełnione
        if (studentId.isEmpty()) {
            outputArea.setText("Error: The 'Student ID' field must be filled to remove a student.");
            return;
        }
        
        try {
            // Sprawdzenie, czy student istnieje
            if (!manager.studentExists(studentId)) { // Zakładamy, że `studentExists` jest metodą w managerze
                outputArea.setText("Error: No student found with ID '" + studentId + "'.");
                return;
            }
            
            // Usuwanie studenta
            manager.removeStudent(studentId);
            outputArea.setText("Student with ID '" + studentId + "' removed successfully!");
        } catch (Exception e) {
            outputArea.setText("Error removing student: " + e.getMessage());
        }
    }
    private void displayAllStudents() {
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
        double average = manager.calculateAverageGrade();
        outputArea.setText("Average Grade: " + average);
    }

    public static void main(String[] args) {
        new StudentManagementGUI();
    }
}
