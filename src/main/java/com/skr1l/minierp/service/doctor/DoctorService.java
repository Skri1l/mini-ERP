package com.skr1l.minierp.service.doctor;

import com.skr1l.minierp.dto.DoctorRequestDto;
import com.skr1l.minierp.entity.Doctor;

import java.util.List;

public interface DoctorService {

    Long addDoctorToClinic(DoctorRequestDto doctorDto, Long clinicId);

    Doctor getDoctorById(Long doctorId);

    List<Doctor> getDoctorsByClinicId(Long clinicId);

    void updateDoctor(DoctorRequestDto doctorDto, Long doctorId);

    void deactivateDoctor(Long doctorId);
}
