package com.hospital.booking.controller;

import com.hospital.booking.dto.PatientDTO;
import com.hospital.booking.dto.PatientStatsDTO;
import com.hospital.booking.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    PatientService patientService;

    @GetMapping("/me/{userId}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<PatientDTO> getMyProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(patientService.getPatientByUserId(userId));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<PatientDTO> updateProfile(@PathVariable Long id, @RequestBody PatientDTO patientDTO) {
        return ResponseEntity.ok(patientService.updatePatient(id, patientDTO));
    }

    @GetMapping("/stats/{id}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<PatientStatsDTO> getStats(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientStats(id));
    }
}
