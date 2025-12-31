package com.hospital.booking.repository;

import com.hospital.booking.entity.Appointment;
import com.hospital.booking.entity.Doctor;
import com.hospital.booking.entity.Patient;
import com.hospital.booking.entity.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatient_PatientId(Long patientId);
    List<Appointment> findByDoctor_DoctorId(Long doctorId);
    
    @Query("SELECT COUNT(a) > 0 FROM Appointment a WHERE a.doctor = :doctor AND a.appointmentDate = :date AND a.timeSlot = :slot AND a.status != 'CANCELLED'")
    boolean isDoctorBooked(Doctor doctor, LocalDate date, String slot);

    @Query("SELECT COUNT(a) > 0 FROM Appointment a WHERE a.patient = :patient AND a.appointmentDate = :date AND a.timeSlot = :slot AND a.status != 'CANCELLED'")
    boolean isPatientBooked(Patient patient, LocalDate date, String slot);

    long countByStatus(com.hospital.booking.entity.AppointmentStatus status);

    long countByDoctor_DoctorId(Long doctorId);
    long countByDoctor_DoctorIdAndStatus(Long doctorId, AppointmentStatus status);
    
    @Query("SELECT COUNT(DISTINCT a.patient) FROM Appointment a WHERE a.doctor.doctorId = :doctorId")
    long countUniquePatientsByDoctorId(Long doctorId);

    long countByPatient_PatientId(Long patientId);
    long countByPatient_PatientIdAndStatus(Long patientId, AppointmentStatus status);
}
