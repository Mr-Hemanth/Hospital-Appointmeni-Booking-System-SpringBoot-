package com.hospital.booking.service;

import com.hospital.booking.dto.JwtResponse;
import com.hospital.booking.dto.LoginRequest;
import com.hospital.booking.dto.SignupRequest;
import com.hospital.booking.entity.Doctor;
import com.hospital.booking.entity.Patient;
import com.hospital.booking.entity.Role;
import com.hospital.booking.entity.User;
import com.hospital.booking.repository.DoctorRepository;
import com.hospital.booking.repository.PatientRepository;
import com.hospital.booking.repository.UserRepository;
import com.hospital.booking.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
        
        Long profileId = null;
        if (user.getRole() == Role.PATIENT) {
            profileId = patientRepository.findByUser(user).get().getPatientId();
        } else if (user.getRole() == Role.DOCTOR) {
            profileId = doctorRepository.findAll().stream()
                    .filter(d -> d.getUser().getUserId().equals(user.getUserId()))
                    .findFirst().get().getDoctorId();
        }

        return new JwtResponse(jwt, user.getEmail(), user.getRole().name(), profileId != null ? profileId : user.getUserId(), user.getName());
    }

    @Transactional
    public void registerUser(SignupRequest signupRequest) {
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new RuntimeException("Error: Email is already in use!");
        }

        User user = new User(null, signupRequest.getName(), signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()), signupRequest.getRole());

        user = userRepository.save(user);

        if (signupRequest.getRole() == Role.PATIENT) {
            Patient patient = new Patient(null, user, signupRequest.getAge(), signupRequest.getGender());
            patientRepository.save(patient);
        } else if (signupRequest.getRole() == Role.DOCTOR) {
            Doctor doctor = new Doctor(null, user, signupRequest.getSpecialization(),
                    signupRequest.getExperience(), signupRequest.getAvailability());
            doctorRepository.save(doctor);
        }
    }
}
