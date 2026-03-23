# INTISG26JFAGN001_Team-01_Training_Management_System_CLI_Repo


A robust, layered Java console application designed to manage students, trainers, courses, and certifications. This project demonstrates the implementation of CRUD operations, JDBC connectivity, and Layered Architecture (DAO/Service).


## Project Overview
The Training Management System provides a platform where:
- Trainers can manage course offerings and view student details.
- Students can browse available courses, enroll, and view their earned certifications.
- Data Integrity is maintained using a MySQL backend with soft-delete capabilities.


## Tech Stack
* Language Java 25
* Database MySQL 8.0
* Build Tool: Maven
* Connectivity: JDBC (Java Database Connectivity)
* Architecture: Layered Pattern (UI -> Service -> DAO -> Database)


## Main Features

### User Management
* Dual Role Login: Separate dashboards for Students and Trainers.
* Secure Registration: Prevents duplicate emails/usernames using custom `UserAlreadyExistsException`.
* Soft Delete: Records are flagged as deleted rather than removed from the database to maintain audit trails.

### Course & Enrollment
* Course Discovery: Students can browse a real-time list of courses created by trainers.
* One-Click Enrollment: Students can join courses; the system prevents double-enrollment in the same course.
* Trainer Insights: Trainers can see a list of all registered students.

### Certification
* Digital Certificates: Automatic generation of unique Certificate IDs (UUID) upon course completion.
* Verification: Students can view their history of certifications in their dashboard.


## Project Structure
```text
com.cognizant
├── config       # Database connection logic
├── dao          # Data Access Object interfaces
├── daoimpl      # JDBC implementations of DAOs
├── model        # POJO classes (Student, Course, etc.)
├── service      # Business logic and validation
├── exception    # Custom exception classes
└── ui           # MenuHandler and CLI logic
```

## UML Diagram

classDiagram

%% =======================
%% MODEL LAYER
%% =======================

class Student {
  -int id
  -String username
  -String email
  +int getId()
  +String getUsername()
}

class Trainer {
  -int id
  -String username
  -String specialization
}

class Course {
  -int id
  -String courseName
}

class Enrollment {
  -int id
  -String status
}

class Certification {
  -int id
  -String certificateUrl
}

%% Relationships (Model)
Student "1" --> "0..*" Enrollment
Course "1" --> "0..*" Enrollment
Trainer "1" --> "0..*" Course : teaches
Student "1" --> "0..*" Certification
Course "1" --> "0..*" Certification


%% =======================
%% DAO LAYER (Interfaces)
%% =======================

class StudentDAO {
  <<interface>>
  +boolean save(Student)
  +Student findByUsername(String)
}

class TrainerDAO {
  <<interface>>
  +boolean save(Trainer)
}

class CourseDAO {
  <<interface>>
  +boolean save(Course)
  +List~Course~ getAllCourses()
}

class EnrollmentDAO {
  <<interface>>
  +boolean enroll(Student, Course)
}

class CertificationDAO {
  <<interface>>
  +boolean issueCertificate(Certification)
}


%% =======================
%% DAO IMPLEMENTATION
%% =======================

class StudentDAOImpl
class TrainerDAOImpl
class CourseDAOImpl
class EnrollmentDAOImpl
class CertificationDAOImpl

StudentDAOImpl --|> StudentDAO
TrainerDAOImpl --|> TrainerDAO
CourseDAOImpl --|> CourseDAO
EnrollmentDAOImpl --|> EnrollmentDAO
CertificationDAOImpl --|> CertificationDAO


%% =======================
%% SERVICE LAYER
%% =======================

class StudentService {
  +boolean register(Student)
  +Student login(String, String)
}

class TrainerService {
  +boolean register(Trainer)
}

class CourseService {
  +boolean addCourse(Course)
  +List~Course~ listCourses()
}

class EnrollmentService {
  +String enroll(Student, Course)
}

class CertificationService {
  +boolean generateCertificate(Student, Course)
}


%% =======================
%% UI LAYER
%% =======================

class MenuHandler {
  +void displayMenu()
}


%% =======================
%% CONFIG
%% =======================

class DatabaseConfig {
  +Connection getConnection()
}


%% =======================
%% DEPENDENCIES (LAYER FLOW)
%% =======================

MenuHandler --> StudentService
MenuHandler --> TrainerService
MenuHandler --> CourseService
MenuHandler --> EnrollmentService
MenuHandler --> CertificationService

StudentService --> StudentDAO
TrainerService --> TrainerDAO
CourseService --> CourseDAO
EnrollmentService --> EnrollmentDAO
CertificationService --> CertificationDAO

StudentDAOImpl --> DatabaseConfig
TrainerDAOImpl --> DatabaseConfig
CourseDAOImpl --> DatabaseConfig
EnrollmentDAOImpl --> DatabaseConfig
CertificationDAOImpl --> DatabaseConfig
