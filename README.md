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

## Sequence Diagram

    actor User as User (CLI)

    participant MenuHandler

    participant EnrollmentService

    participant EnrollmentDAO

    participant DB as MySQL Database



    User->>MenuHandler: Enter courseId

    MenuHandler->>EnrollmentService: enrollInCourse(studentId, courseId)



    EnrollmentService->>EnrollmentDAO: checkEnrollment(studentId, courseId)

    EnrollmentDAO->>DB: SELECT * FROM enrollment WHERE studentId, courseId



    DB-->>EnrollmentDAO: Result (exists / not exists)

    EnrollmentDAO-->>EnrollmentService: enrollment status



    alt Already Enrolled

        EnrollmentService-->>MenuHandler: "Already Enrolled"

        MenuHandler-->>User: Show message

    else Not Enrolled

        EnrollmentService->>EnrollmentDAO: enroll(studentId, courseId)

        EnrollmentDAO->>DB: INSERT INTO enrollment



        DB-->>EnrollmentDAO: Success / Failure

        EnrollmentDAO-->>EnrollmentService: status



        EnrollmentService-->>MenuHandler: Enrollment result

        MenuHandler-->>User: Display success/failure

    end

## Flowchart

A[Start Application] --> B[Display Main Menu]

    B --> C{Select Role}
    C -->|Student| D[Student Login/Register]
    C -->|Trainer| E[Trainer Login/Register]

    D --> F[Validate Credentials]
    E --> F

    F -->|Success| G{Role Type}
    F -->|Failure| B

    G -->|Student| H[Student Dashboard]
    G -->|Trainer| I[Trainer Dashboard]

    H --> J{Choose Action}
    I --> K{Choose Action}

    J -->|Enroll Course| L[Enrollment Flow]
    J -->|View Courses| M[List Courses]
    J -->|Logout| N[Logout]

    K -->|Add Course| O[Create Course]
    K -->|View Courses| M
    K -->|Logout| N

    L --> H
    M --> H
    O --> I

    N --> B
