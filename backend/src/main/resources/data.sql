-- 1. Admin User
-- Password: password123
INSERT IGNORE INTO users (user_id, name, email, password, role) VALUES 
(1, 'Admin User', 'admin@hospital.com', '$2a$10$cxlo42QPjARLq/IIFIgxIe2HP.1TWab116hnERwbpBb/WSm5NmMw2', 'ADMIN');

-- 2. Doctor Users
-- Password: password123
INSERT IGNORE INTO users (user_id, name, email, password, role) VALUES 
(2, 'Dr. John Smith', 'smith@hospital.com', '$2a$10$cxlo42QPjARLq/IIFIgxIe2HP.1TWab116hnERwbpBb/WSm5NmMw2', 'DOCTOR'),
(4, 'Dr. Sarah Wilson', 'wilson@hospital.com', '$2a$10$cxlo42QPjARLq/IIFIgxIe2HP.1TWab116hnERwbpBb/WSm5NmMw2', 'DOCTOR'),
(5, 'Dr. Michael Chen', 'chen@hospital.com', '$2a$10$cxlo42QPjARLq/IIFIgxIe2HP.1TWab116hnERwbpBb/WSm5NmMw2', 'DOCTOR');

INSERT IGNORE INTO doctors (doctor_id, user_id, specialization, experience, availability) VALUES 
(1, 2, 'Cardiology', 15, 'Mon-Fri 09:00 AM - 05:00 PM'),
(2, 4, 'Neurology', 12, 'Tue-Sat 10:00 AM - 06:00 PM'),
(3, 5, 'Pediatrics', 8, 'Mon-Thu 08:00 AM - 04:00 PM');

-- 3. Patient User
-- Password: password123
INSERT IGNORE INTO users (user_id, name, email, password, role) VALUES 
(3, 'Jane Doe', 'jane@gmail.com', '$2a$10$cxlo42QPjARLq/IIFIgxIe2HP.1TWab116hnERwbpBb/WSm5NmMw2', 'PATIENT');

INSERT IGNORE INTO patients (patient_id, user_id, age, gender) VALUES 
(1, 3, 28, 'FEMALE');
