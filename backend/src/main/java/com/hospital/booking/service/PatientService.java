package com.hospital.booking.service;

import com.hospital.booking.dto.PatientDTO;
import com.hospital.booking.dto.PatientStatsDTO;
import com.hospital.booking.entity.AppointmentStatus;
import com.hospital.booking.entity.Patient;
import com.hospital.booking.entity.User;
import com.hospital.booking.repository.AppointmentRepository;
import com.hospital.booking.repository.PatientRepository;
import com.hospital.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    public PatientDTO getPatientById(Long id) {
        return patientRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
    }

    public PatientDTO getPatientByUserId(Long userId) {
        return patientRepository.findByUser_UserId(userId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Patient not found for user id: " + userId));
    }

    @Transactional
    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        Patient patient = patientRepository.findById(id).orElseThrow();
        patient.setAge(patientDTO.getAge());
        patient.setGender(patientDTO.getGender());
        
        User user = patient.getUser();
        user.setName(patientDTO.getName());
        userRepository.save(user);
        
        return convertToDTO(patientRepository.save(patient));
    }

    public PatientStatsDTO getPatientStats(Long patientId) {
        long total = appointmentRepository.countByPatient_PatientId(patientId);
        long pending = appointmentRepository.countByPatient_PatientIdAndStatus(patientId, AppointmentStatus.PENDING);
        long approved = appointmentRepository.countByPatient_PatientIdAndStatus(patientId, AppointmentStatus.APPROVED);
        long cancelled = appointmentRepository.countByPatient_PatientIdAndStatus(patientId, AppointmentStatus.CANCELLED);
        
        return new PatientStatsDTO(total, pending + approved, approved, cancelled);
    }

    private PatientDTO convertToDTO(Patient patient) {
        PatientDTO dto = new PatientDTO();
        dto.setPatientId(patient.getPatientId());
        dto.setUserId(patient.getUser().getUserId());
        dto.setName(patient.getUser().getName());
        dto.setEmail(patient.getUser().getEmail());
        dto.setAge(patient.getAge());
        dto.setGender(patient.getGender());
        return dto;
    }
}
