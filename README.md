# MediBook - Hospital Appointment Booking System

MediBook is a production-ready, full-stack hospital appointment management system built with modern technologies. It provides a seamless experience for patients to book appointments, doctors to manage their schedules, and administrators to oversee the hospital's operations.

## 🚀 Key Features

### **For Patients**
- **Intuitive Dashboard**: View upcoming and past appointments.
- **Doctor Discovery**: Search and filter doctors by specialization and experience.
- **Smart Booking**: Conflict-aware appointment scheduling with real-time feedback.
- **Secure Profiles**: Manage personal health profiles and history.

### **For Doctors**
- **Schedule Management**: View all scheduled appointments in a clean list.
- **Actionable Controls**: Approve or cancel appointments with a single click.
- **Profile Overview**: See patient details and appointment history.

### **For Administrators**
- **Comprehensive Control**: Manage the entire medical staff (Add/Edit/Delete doctors).
- **System Oversight**: Monitor all appointments across the hospital.
- **User Management**: Oversee all registered users and their roles.

## 🛠 Tech Stack

### **Frontend**
- **React 19**: Modern UI development with Hooks and Context API.
- **Tailwind CSS**: Utility-first CSS for a "top-notch" responsive design.
- **Lucide React**: Beautiful, consistent iconography.
- **Axios**: Robust HTTP client for API communication.
- **React Router**: Seamless client-side navigation with role-based guards.

### **Backend**
- **Java 17 & Spring Boot 3.2.1**: High-performance, enterprise-grade REST API.
- **Spring Security & JWT**: Secure token-based authentication and authorization.
- **Spring Data JPA**: Efficient database abstraction and management.
- **MySQL**: Reliable relational database for critical health data.

## 📋 Prerequisites

Before you begin, ensure you have the following installed:
- **Java 17** or higher
- **Node.js 18** or higher
- **MySQL 8.0** or higher
- **Maven 3.8** or higher

## ⚙️ Installation & Setup

### **1. Database Configuration**
1. Log in to your MySQL server.
2. Create a database: `CREATE DATABASE hospital_booking;`
3. Execute the script in `database_schema.sql` to initialize tables and sample data.

### **2. Backend Setup**
1. Navigate to the `backend` directory: `cd backend`
2. Open `src/main/resources/application.properties`.
3. **CRITICAL**: Update `spring.datasource.username` and `spring.datasource.password` to match your local MySQL credentials. 
   - If you get an "Access Denied" error, double-check your password.
   - If your root user has no password, leave `spring.datasource.password=` empty.
4. Build and run:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
5. The backend will be available at `http://localhost:8080`.

### **3. Frontend Setup**
1. Navigate to the `frontend` directory: `cd frontend`
2. Install dependencies: `npm install`
3. Run the development server: `npm start`
4. Access the app at `http://localhost:3000`.

## 🔐 Default Credentials

All sample users share the same default password for easy testing.

**Password for all users:** `password123`

| Role | Email |
| :--- | :--- |
| **Admin** | `admin@hospital.com` |
| **Doctor** | `smith@hospital.com` |
| **Patient** | `jane@gmail.com` |

## 📁 Project Structure

```text
├── backend/                # Spring Boot Backend
│   ├── src/main/java/      # Java Source Code
│   ├── src/main/resources/ # Configuration & Properties
│   └── pom.xml             # Maven Dependencies
├── frontend/               # React Frontend
│   ├── src/components/     # Reusable UI Components
│   ├── src/pages/          # Main View Components
│   ├── src/context/        # Auth & Global State
│   └── package.json        # Frontend Dependencies
├── database_schema.sql     # MySQL Database Initialization
└── README.md               # Project Documentation
```

## 🛡 Security Implementation
- **JWT Authentication**: Tokens are issued upon login and validated for every protected request.
- **RBAC (Role-Based Access Control)**: UI elements and API endpoints are restricted based on user roles (Patient, Doctor, Admin).
- **Password Hashing**: BCrypt is used for one-way encryption of user passwords.

## 🚀 Deployment Readiness
- Included `.gitignore` for standard repository hygiene.
- Clean separation of frontend and backend.
- Environment-ready configurations.
- No boilerplate errors or warnings.

---
Developed with ❤️ for top-notch healthcare management.
