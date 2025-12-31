-- Create Database
CREATE DATABASE IF NOT EXISTS hospital_booking;
USE hospital_booking;

-- Users Table
CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('PATIENT', 'DOCTOR', 'ADMIN') NOT NULL
);

-- Doctors Table
CREATE TABLE doctors (
    doctor_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    specialization VARCHAR(255) NOT NULL,
    experience INT NOT NULL,
    availability TEXT,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Patients Table
CREATE TABLE patients (
    patient_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    age INT NOT NULL,
    gender VARCHAR(20) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Departments Table
CREATE TABLE departments (
    department_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    department_name VARCHAR(255) NOT NULL UNIQUE
);

-- Appointments Table
CREATE TABLE appointments (
    appointment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    doctor_id BIGINT NOT NULL,
    appointment_date DATE NOT NULL,
    time_slot VARCHAR(50) NOT NULL,
    status ENUM('PENDING', 'APPROVED', 'CANCELLED') NOT NULL DEFAULT 'PENDING',
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id) ON DELETE CASCADE
);

-- Sample Data
-- Note: All sample users have the password: password123
-- BCrypt hash for 'password123': $2a$10$cxlo42QPjARLq/IIFIgxIe2HP.1TWab116hnERwbpBb/WSm5NmMw2

-- 1. Admin User
INSERT INTO users (name, email, password, role) VALUES 
('Admin User', 'admin@hospital.com', '$2a$10$cxlo42QPjARLq/IIFIgxIe2HP.1TWab116hnERwbpBb/WSm5NmMw2', 'ADMIN');

-- 2. Doctor User
INSERT INTO users (name, email, password, role) VALUES 
('Dr. John Smith', 'smith@hospital.com', '$2a$10$cxlo42QPjARLq/IIFIgxIe2HP.1TWab116hnERwbpBb/WSm5NmMw2', 'DOCTOR');
INSERT INTO doctors (user_id, specialization, experience, availability) VALUES 
(2, 'Cardiology', 15, 'Mon-Fri 09:00 AM - 05:00 PM');

-- 3. Patient User
INSERT INTO users (name, email, password, role) VALUES 
('Jane Doe', 'jane@gmail.com', '$2a$10$cxlo42QPjARLq/IIFIgxIe2HP.1TWab116hnERwbpBb/WSm5NmMw2', 'PATIENT');
INSERT INTO patients (user_id, age, gender) VALUES 
(3, 28, 'FEMALE');

-- Departments
INSERT INTO departments (department_name) VALUES 
('Cardiology'), ('Neurology'), ('Orthopedics'), ('Pediatrics'), ('Dermatology');
