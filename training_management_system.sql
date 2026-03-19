CREATE DATABASE IF NOT EXISTS training_management_system;
USE training_management_system;

-- 1. STUDENT TABLE
CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    mobile_number VARCHAR(15) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    -- Audit Fields
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    -- Soft Delete
    is_deleted BOOLEAN DEFAULT FALSE
);

-- 2. TRAINER TABLE
CREATE TABLE trainers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    mobile_number VARCHAR(15) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    specialization VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE
);

-- 3. COURSE TABLE
CREATE TABLE courses (
    id INT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(100) NOT NULL,
    description TEXT,
    trainer_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (trainer_id) REFERENCES trainers(id)
);

-- 4. ENROLLMENT TABLE (Mapping Students to Courses)
CREATE TABLE enrollments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    enrollment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('ACTIVE', 'COMPLETED', 'CANCELLED') DEFAULT 'ACTIVE',
    is_deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (course_id) REFERENCES courses(id)
);

-- 5. CERTIFICATION TABLE
CREATE TABLE certifications (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    issue_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    certificate_url VARCHAR(255), -- Link or reference to a generated file
    is_deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (course_id) REFERENCES courses(id)
);

-- adding sample data in courses table

USE training_management_system;

SET @trainer_id = (SELECT id FROM trainers WHERE username = 'Kalp' LIMIT 1);

INSERT INTO courses (course_name, description, trainer_id) VALUES 
('Java Core Fundamentals', 'Learn basic Java, OOPs, and Collections.', @trainer_id),
('Advanced JDBC & MySQL', 'Deep dive into database connectivity and SQL.', @trainer_id),
('Spring Boot Microservices', 'Building scalable web applications.', @trainer_id),
('Web Development with React', 'Modern frontend development with React JS.', @trainer_id),
('Python for Data Science', 'Introduction to NumPy, Pandas, and Matplotlib.', @trainer_id);


SELECT * FROM students;