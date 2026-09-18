package com.skr1l.minierp.service.Doctor;

import com.skr1l.minierp.dto.DoctorRequestDto;
import com.skr1l.minierp.entity.Clinic;
import com.skr1l.minierp.entity.Doctor;
import com.skr1l.minierp.exception.ClinicNotFoundException;
import com.skr1l.minierp.exception.DoctorNotFoundException;
import com.skr1l.minierp.repository.ClinicRepository;
import com.skr1l.minierp.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    private final ClinicRepository clinicRepository;

    @Override
    @Transactional
    public Long addDoctorToClinic(DoctorRequestDto doctorDto, Long clinicId) {

        Objects.requireNonNull(doctorDto, "doctorDto is null");
        Objects.requireNonNull(clinicId, "clinicId is null");

        Doctor doctor = new Doctor();

        doctor.setFirstName(doctorDto.firstName());
        doctor.setLastName(doctorDto.lastName());
        doctor.setEmail(doctorDto.email());
        doctor.setPhoneNumber(doctorDto.phoneNumber());
        doctor.setSpecialization(doctorDto.specialization());
        doctor.setPhotoUrl(doctorDto.photoUrl());
        doctor.setActive(true);

        Clinic clinic = clinicRepository.findById(clinicId)
                .orElseThrow(() -> new ClinicNotFoundException(
                        "Clinic with id " + clinicId + " not found"));

        doctor.setClinic(clinic);

        doctorRepository.save(doctor);

        return doctor.getId();
    }

    @Override
    @Transactional
    public Doctor getDoctorById(Long doctorId) {

        Objects.requireNonNull(doctorId, "doctorId is null");

        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor with id " + doctorId + " not found"));
    }

    @Override
    @Transactional
    public List<Doctor> getDoctorsByClinicId(Long clinicId) {

        Objects.requireNonNull(clinicId, "clinicId is null");

        clinicRepository.findById(clinicId)
                .orElseThrow(() -> new ClinicNotFoundException("Clinic with id " + clinicId + " not found"));

        return doctorRepository.findAllByClinicId(clinicId);
    }

    @Override
    @Transactional
    public void updateDoctor(DoctorRequestDto doctorDto, Long doctorId) {

        Objects.requireNonNull(doctorDto, "doctorDto is null");
        Objects.requireNonNull(doctorId, "doctorId is null");

        Doctor doctor = getDoctorById(doctorId);

        doctor.setFirstName(doctorDto.firstName());
        doctor.setLastName(doctorDto.lastName());
        doctor.setEmail(doctorDto.email());
        doctor.setPhoneNumber(doctorDto.phoneNumber());
        doctor.setPhotoUrl(doctorDto.photoUrl());
        doctor.setSpecialization(doctorDto.specialization());

        doctorRepository.save(doctor);
    }

    @Override
    @Transactional
    public void deactivateDoctor(Long doctorId) {

        Objects.requireNonNull(doctorId, "doctorId is null");

        Doctor doctor = getDoctorById(doctorId);

        doctor.setActive(false);
        doctorRepository.save(doctor);
    }
}
