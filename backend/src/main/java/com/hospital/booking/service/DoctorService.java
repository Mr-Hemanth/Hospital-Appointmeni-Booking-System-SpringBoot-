package com.hospital.booking.service;

import com.hospital.booking.dto.DoctorDTO;
import com.hospital.booking.dto.DoctorStatsDTO;
import com.hospital.booking.entity.AppointmentStatus;
import com.hospital.booking.entity.Doctor;
import com.hospital.booking.entity.Role;
import com.hospital.booking.entity.User;
import com.hospital.booking.repository.AppointmentRepository;
import com.hospital.booking.repository.DoctorRepository;
import com.hospital.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    PasswordEncoder encoder;

    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<DoctorDTO> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public DoctorDTO getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
    }

    public DoctorDTO getDoctorByUserId(Long userId) {
        return doctorRepository.findByUser_UserId(userId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Doctor not found for user id: " + userId));
    }

    public DoctorStatsDTO getDoctorStats(Long doctorId) {
        long total = appointmentRepository.countByDoctor_DoctorId(doctorId);
        long pending = appointmentRepository.countByDoctor_DoctorIdAndStatus(doctorId, AppointmentStatus.PENDING);
        long approved = appointmentRepository.countByDoctor_DoctorIdAndStatus(doctorId, AppointmentStatus.APPROVED);
        long cancelled = appointmentRepository.countByDoctor_DoctorIdAndStatus(doctorId, AppointmentStatus.CANCELLED);
        long uniquePatients = appointmentRepository.countUniquePatientsByDoctorId(doctorId);
        
        return new DoctorStatsDTO(total, pending, approved, cancelled, uniquePatients);
    }

    @Transactional
    public DoctorDTO addDoctor(DoctorDTO doctorDTO) {
        User user = new User();
        user.setName(doctorDTO.getName());
        user.setEmail(doctorDTO.getEmail());
        user.setPassword(encoder.encode(doctorDTO.getPassword() != null ? doctorDTO.getPassword() : "password123"));
        user.setRole(Role.DOCTOR);
        user = userRepository.save(user);

        Doctor doctor = new Doctor();
        doctor.setUser(user);
        doctor.setSpecialization(doctorDTO.getSpecialization());
        doctor.setExperience(doctorDTO.getExperience());
        doctor.setAvailability(doctorDTO.getAvailability());
        
        return convertToDTO(doctorRepository.save(doctor));
    }

    @Transactional
    public DoctorDTO updateDoctor(Long id, DoctorDTO doctorDTO) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
        doctor.setSpecialization(doctorDTO.getSpecialization());
        doctor.setExperience(doctorDTO.getExperience());
        doctor.setAvailability(doctorDTO.getAvailability());
        
        User user = doctor.getUser();
        user.setName(doctorDTO.getName());
        userRepository.save(user);
        
        return convertToDTO(doctorRepository.save(doctor));
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    private DoctorDTO convertToDTO(Doctor doctor) {
        DoctorDTO dto = new DoctorDTO();
        dto.setDoctorId(doctor.getDoctorId());
        dto.setName(doctor.getUser().getName());
        dto.setEmail(doctor.getUser().getEmail());
        dto.setSpecialization(doctor.getSpecialization());
        dto.setExperience(doctor.getExperience());
        dto.setAvailability(doctor.getAvailability());
        return dto;
    }
}
