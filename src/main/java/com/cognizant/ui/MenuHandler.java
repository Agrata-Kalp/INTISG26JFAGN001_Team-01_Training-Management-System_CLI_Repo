package com.cognizant.ui;

import com.cognizant.daoImpl.StudentDAOImpl;
import com.cognizant.exception.TrainerNotFoundException;
import com.cognizant.exception.UserAlreadyExistsException;
import com.cognizant.model.Course;
import com.cognizant.model.Student;
import com.cognizant.model.Trainer;
import com.cognizant.service.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuHandler {
    private final Scanner scanner = new Scanner(System.in);
    private final StudentDAOImpl studentDAO = new StudentDAOImpl();
    private final StudentService studentService = new StudentService();
    private final TrainerService trainerService = new TrainerService();
    private final CourseService courseService = new CourseService();
    private final EnrollmentService enrollmentService = new EnrollmentService();
    private final CertificationService certificationService = new CertificationService();

    public void displayMainMenu() {
        while (true) {
            System.out.println("\n--- Welcome to Training Management System !! ---");
            System.out.println("Select your role:");
            System.out.println("1. Student");
            System.out.println("2. Trainer");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> displayUserMenu("Student");
                case 2 -> displayUserMenu("Trainer");
                case 3 -> {
                    System.out.println("Exiting... Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void displayUserMenu(String role) {
        System.out.println("\n--- " + role + " Portal ---");
        System.out.println("1. Login " + role);
        System.out.println("2. Register " + role);
        System.out.println("3. Back to Main Menu");
        System.out.print("Enter choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (role.equalsIgnoreCase("Student")) {
            handleStudentActions(choice);
        } else {
            handleTrainerActions(choice);
        }
    }

    private void handleStudentActions(int choice) {
        try {
            if (choice == 1) { // Login
                System.out.print("Username: "); String uname = scanner.nextLine();
                System.out.print("Password: "); String pwd = scanner.nextLine();

                Student s = studentService.login(uname, pwd);
                if (s != null) {
                    System.out.println("\nLogin Successful!");
                    showStudentDashboard(s); // This is your "Redirect"
                } else {
                    System.out.println("\nLogin Failed.");
                }
            } else if (choice == 2) { // REGISTER
                System.out.println("\n--- Student Registration ---");

                System.out.print("Enter Username: ");
                String uname = scanner.nextLine();

                System.out.print("Enter Email: ");
                String email = scanner.nextLine();

                System.out.print("Enter Mobile Number: ");
                String mobile = scanner.nextLine();

                System.out.print("Enter Password: ");
                String pwd = scanner.nextLine();

                // Create the model object with the collected data
                Student newStudent = new Student(uname, email, mobile, pwd);

                // Pass the object to the service
                if (studentService.registerStudent(newStudent)) {
                    System.out.println("\nStudent registered successfully!");
                }
            }
        } catch (UserAlreadyExistsException e) {
            // This catches our custom exception and shows the friendly message
            System.out.println("\n" + e.getMessage());
        } catch (SQLException e) {
            // This catches general database issues (connection lost, etc.)
            System.out.println("\n Database Error: Please check your connection.");
            e.printStackTrace(); // Useful for debugging during development
        }
    }

    private void handleTrainerActions(int choice) {
        try {
            if (choice == 1) { // Login
                System.out.print("Username: "); String uname = scanner.nextLine();
                System.out.print("Password: "); String pwd = scanner.nextLine();

                Trainer t = trainerService.login(uname, pwd);
                if (t != null) {
                    System.out.println("\nTrainer Login Successful!");
                    showTrainerDashboard(t); // This is your "Redirect"
                } else {
                    System.out.println("\nLogin Failed.");
                }
            } else if (choice == 2) { // REGISTER
                System.out.print("Username: "); String uname = scanner.nextLine();
                System.out.print("Email: "); String email = scanner.nextLine();
                System.out.print("Mobile: "); String mobile = scanner.nextLine();
                System.out.print("Password: "); String pwd = scanner.nextLine();
                System.out.print("Specialization (Java/SQL/etc): "); String spec = scanner.nextLine();

                Trainer newTrainer = new Trainer(uname, email, mobile, pwd, spec);
                if (trainerService.registerTrainer(newTrainer)) {
                    System.out.println("\nTrainer registered successfully!");
                }
            }
        } catch (UserAlreadyExistsException e) {
            System.out.println("\n" + e.getMessage());
        } catch (SQLException e) {
            System.out.println("\nDatabase Error.");
        } catch (TrainerNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void showStudentDashboard(Student student) {
        while (true) {
            System.out.println("\n===== STUDENT DASHBOARD =====");
            System.out.println("Welcome, " + student.getUsername() + "!");
            System.out.println("1. Browse Available Courses");
            System.out.println("2. Enroll in a Course");
            System.out.println("3. My Enrolled Courses");
            System.out.println("4. My Certifications");
            System.out.println("5. Log Out");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> { // Browse Available Courses
                        System.out.println("\n--- Available Courses ---");
                        List<Course> courses = courseService.listAllAvailableCourses();

                        if (courses.isEmpty()) {
                            System.out.println("No courses available at the moment.");
                        } else {
                            for (Course c : courses) {
                                System.out.println("ID: " + c.getId() + " | Name: " + c.getCourseName() + " | Description: " + c.getDescription());
                            }
                        }
                    }
                    case 2 -> { // Enroll
                        System.out.print("Enter Course ID to enroll: ");
                        int cId = scanner.nextInt();
                        System.out.println(enrollmentService.enrollInCourse(student.getId(), cId));
                    }
                    case 3 -> { // My Courses
                        System.out.println("\n--- Your Enrolled Courses ---");
                        enrollmentService.getStudentEnrollments(student.getId()).forEach(e ->
                                System.out.println("Course ID: " + e.getCourseId() + " | Status: " + e.getStatus()));
                    }
                    case 4 -> { // My Certifications
                        System.out.println("\n--- Your Certifications ---");
                        certificationService.getMyCertifications(student.getId()).forEach(cert ->
                                System.out.println("Cert ID: " + cert.getCertificateUrl()));
                    }
                    case 5 -> {
                        System.out.println("Logging out of Student Dashboard...");
                        return; // Returns to Role Selection Menu
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void showTrainerDashboard(Trainer trainer) {
        while (true) {
            System.out.println("\n===== TRAINER DASHBOARD =====");
            System.out.println("Welcome, Coach " + trainer.getUsername() + "!");
            System.out.println("1. View Student Details");
            System.out.println("2. My Courses");
            System.out.println("3. Certify Student");
            System.out.println("4. Log Out");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> { // Student Details
                        System.out.println("\n--- Registered Students List ---");
                        try {
                            List<Student> students = studentService.getAllStudents();
                            if (students.isEmpty()) {
                                System.out.println("No students registered yet.");
                            } else {
                                System.out.printf("%-5s | %-15s | %-20s | %-15s\n", "ID", "Username", "Email", "Mobile");
                                System.out.println("------------------------------------------------------------");
                                for (Student s : students) {
                                    System.out.printf("%-5d | %-15s | %-20s | %-15s\n",
                                            s.getId(), s.getUsername(), s.getEmail(), s.getMobileNumber());
                                }
                            }
                        } catch (SQLException e) {
                            System.out.println("Error fetching students: " + e.getMessage());
                        }
                    }
                    case 2 -> {
                        System.out.println("\n--- Courses You Manage ---");
                        courseService.listAllAvailableCourses().stream()
                                .filter(c -> c.getTrainerId() == trainer.getId())
                                .forEach(c -> System.out.println("ID: " + c.getId() + " | Name: " + c.getCourseName()));
                    }
                    case 3 -> {
                        System.out.print("Enter Student ID to certify: ");
                        int sId = scanner.nextInt();
                        System.out.print("Enter Course ID: ");
                        int cId = scanner.nextInt();
                        scanner.nextLine();

                        if (certificationService.generateCertificate(sId, cId)) {
                            System.out.println("Certificate issued successfully for Student ID: " + sId);
                        } else {
                            System.out.println("Failed to issue certificate.");
                        }
                    }
                    case 4 -> {
                        System.out.println("Logging out of Trainer Dashboard...");
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}