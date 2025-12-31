package com.hospital.booking.repository;

import com.hospital.booking.entity.Patient;
import com.hospital.booking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByUser(User user);
    Optional<Patient> findByUser_UserId(Long userId);
}
