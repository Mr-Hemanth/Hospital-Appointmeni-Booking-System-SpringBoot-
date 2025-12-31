/**
 * HOSPITAL APPOINTMENT BOOKING SYSTEM - PROJECT DOCUMENTATION
 * 
 * This file provides a comprehensive overview of the project, features, 
 * tech stack, and setup instructions.
 */

const ProjectInfo = {
  name: "MediBook - Hospital Appointment Booking System",
  version: "1.0.0",
  description: "A production-ready full-stack application for managing hospital appointments, doctors, and patients.",
  
  techStack: {
    frontend: {
      framework: "React 18",
      styling: "Tailwind CSS",
      icons: "Lucide React",
      routing: "React Router DOM",
      http_client: "Axios",
      state_management: "React Context API"
    },
    backend: {
      framework: "Spring Boot 3.x",
      language: "Java 17",
      security: "Spring Security with JWT (JSON Web Tokens)",
      database_access: "Spring Data JPA",
      database: "MySQL 8.x",
      build_tool: "Maven"
    }
  },

  coreFeatures: [
    {
      role: "Patient",
      capabilities: [
        "Secure registration and login",
        "Browse doctors by specialization",
        "Book appointments with conflict checking",
        "View and track personal appointment history",
        "Modern, responsive user interface"
      ]
    },
    {
      role: "Doctor",
      capabilities: [
        "Manage patient appointments",
        "Approve or Cancel booking requests",
        "Real-time status updates",
        "Daily schedule overview"
      ]
    },
    {
      role: "Administrator",
      capabilities: [
        "Full doctor management (Add/Edit/Delete)",
        "System-wide appointment monitoring",
        "User role management",
        "Dashboard statistics"
      ]
    }
  ],

  setupInstructions: {
    prerequisites: [
      "Java 17 or higher",
      "Node.js 16 or higher",
      "MySQL 8.x",
      "Maven"
    ],
    databaseSetup: [
      "Create a MySQL database named 'hospital_booking'",
      "Execute the schema provided in 'database_schema.sql'",
      "Update 'backend/src/main/resources/application.properties' with your credentials"
    ],
    backendExecution: [
      "cd backend",
      "mvn clean install",
      "mvn spring-boot:run"
    ],
    frontendExecution: [
      "cd frontend",
      "npm install",
      "npm start"
    ]
  },

  securityHighlights: [
    "JWT-based stateless authentication",
    "Role-Based Access Control (RBAC)",
    "Password encryption using BCrypt",
    "CORS configuration for frontend-backend communication",
    "Protected routes in React frontend"
  ],

  author: "Hemanth.K Projects",
  status: "Production Ready"
};

console.log(`Welcome to ${ProjectInfo.name} v${ProjectInfo.version}`);
console.log("Check the README.md for more detailed technical documentation.");

export default ProjectInfo;
