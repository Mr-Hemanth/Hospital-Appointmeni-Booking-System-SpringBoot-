package com.hospital.booking.service;

import com.hospital.booking.dto.AppointmentDTO;
import com.hospital.booking.entity.Appointment;
import com.hospital.booking.entity.AppointmentStatus;
import com.hospital.booking.entity.Doctor;
import com.hospital.booking.entity.Patient;
import com.hospital.booking.repository.AppointmentRepository;
import com.hospital.booking.repository.DoctorRepository;
import com.hospital.booking.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoctorRepository doctorRepository;

    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AppointmentDTO> getPatientAppointments(Long patientId) {
        return appointmentRepository.findByPatient_PatientId(patientId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AppointmentDTO> getDoctorAppointments(Long doctorId) {
        return appointmentRepository.findByDoctor_DoctorId(doctorId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AppointmentDTO bookAppointment(AppointmentDTO dto) {
        Patient patient = patientRepository.findById(dto.getPatientId()).orElseThrow();
        Doctor doctor = doctorRepository.findById(dto.getDoctorId()).orElseThrow();

        if (appointmentRepository.isDoctorBooked(doctor, dto.getAppointmentDate(), dto.getTimeSlot())) {
            throw new RuntimeException("Conflict: Doctor is already booked for this time slot!");
        }

        if (appointmentRepository.isPatientBooked(patient, dto.getAppointmentDate(), dto.getTimeSlot())) {
            throw new RuntimeException("Conflict: You already have an appointment at this time!");
        }

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setTimeSlot(dto.getTimeSlot());
        appointment.setStatus(AppointmentStatus.PENDING);

        return convertToDTO(appointmentRepository.save(appointment));
    }

    public AppointmentDTO updateStatus(Long appointmentId, AppointmentStatus status) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        appointment.setStatus(status);
        return convertToDTO(appointmentRepository.save(appointment));
    }

    public AppointmentDTO updateNotes(Long appointmentId, String notes) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        appointment.setNotes(notes);
        return convertToDTO(appointmentRepository.save(appointment));
    }

    private AppointmentDTO convertToDTO(Appointment appointment) {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setAppointmentId(appointment.getAppointmentId());
        dto.setPatientId(appointment.getPatient().getPatientId());
        dto.setPatientName(appointment.getPatient().getUser().getName());
        dto.setDoctorId(appointment.getDoctor().getDoctorId());
        dto.setDoctorName(appointment.getDoctor().getUser().getName());
        dto.setSpecialization(appointment.getDoctor().getSpecialization());
        dto.setAppointmentDate(appointment.getAppointmentDate());
        dto.setTimeSlot(appointment.getTimeSlot());
        dto.setStatus(appointment.getStatus());
        dto.setNotes(appointment.getNotes());
        return dto;
    }
}
