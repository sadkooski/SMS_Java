package model;

// Klasa reprezentująca studenta
public class Student {
    private String name;       // Imię studenta
    private int age;           // Wiek studenta
    private double grade;      // Ocena studenta
    private String studentID;  // Unikalny identyfikator studenta

    // Konstruktor klasy Student
    public Student(String name, int age, double grade, String studentID) {
        this.name = name;           // Przypisanie imienia
        this.age = age;             // Przypisanie wieku
        this.grade = grade;         // Przypisanie oceny
        this.studentID = studentID; // Przypisanie ID studenta
    }

    // Gettery i settery dla pól klasy

    // Pobiera imię studenta
    public String getName() {
        return name;
    }

    // Ustawia imię studenta
    public void setName(String name) {
        this.name = name;
    }

    // Pobiera wiek studenta
    public int getAge() {
        return age;
    }

    // Ustawia wiek studenta z walidacją
    public void setAge(int age) {
        if (age > 0) { // Wiek musi być dodatni
            this.age = age;
        } else {
            throw new IllegalArgumentException("Age must be positive.");
        }
    }

    // Pobiera ocenę studenta
    public double getGrade() {
        return grade;
    }

    // Ustawia ocenę studenta z walidacją
    public void setGrade(double grade) {
        if (grade >= 0.0 && grade <= 100.0) { // Ocena musi być w zakresie 0.0–100.0
            this.grade = grade;
        } else {
            throw new IllegalArgumentException("Grade must be between 0.0 and 100.0.");
        }
    }

    // Pobiera identyfikator studenta
    public String getStudentID() {
        return studentID;
    }

    // Ustawia identyfikator studenta
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    // Wyświetla informacje o studencie
    public void displayInfo() {
        // Wypisanie ID studenta, imienia, wieku i oceny na konsoli
        System.out.println("Student ID: " + studentID);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Grade: " + grade);
    }
}
