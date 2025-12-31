-- 1. Admin User
-- Password: password123
INSERT IGNORE INTO users (user_id, name, email, password, role) VALUES 
(1, 'Admin User', 'admin@hospital.com', '$2a$10$cxlo42QPjARLq/IIFIgxIe2HP.1TWab116hnERwbpBb/WSm5NmMw2', 'ADMIN');

-- 2. Doctor User
-- Password: password123
INSERT IGNORE INTO users (user_id, name, email, password, role) VALUES 
(2, 'Dr. John Smith', 'smith@hospital.com', '$2a$10$cxlo42QPjARLq/IIFIgxIe2HP.1TWab116hnERwbpBb/WSm5NmMw2', 'DOCTOR');

INSERT IGNORE INTO doctors (doctor_id, user_id, specialization, experience, availability) VALUES 
(1, 2, 'Cardiology', 15, 'Mon-Fri 09:00 AM - 05:00 PM');

-- 3. Patient User
-- Password: password123
INSERT IGNORE INTO users (user_id, name, email, password, role) VALUES 
(3, 'Jane Doe', 'jane@gmail.com', '$2a$10$cxlo42QPjARLq/IIFIgxIe2HP.1TWab116hnERwbpBb/WSm5NmMw2', 'PATIENT');

INSERT IGNORE INTO patients (patient_id, user_id, age, gender) VALUES 
(1, 3, 28, 'FEMALE');
