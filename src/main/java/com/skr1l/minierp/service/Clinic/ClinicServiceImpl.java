package com.skr1l.minierp.service.Clinic;

import com.skr1l.minierp.dto.ClinicRequestDto;
import com.skr1l.minierp.entity.Clinic;
import com.skr1l.minierp.exception.ClinicNotFoundException;
import com.skr1l.minierp.repository.ClinicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ClinicServiceImpl implements ClinicService {

    private final ClinicRepository clinicRepository;

    @Override
    @Transactional
    public Long createClinic(ClinicRequestDto clinicDto) {

        Objects.requireNonNull(clinicDto, "clinic is null");

        Clinic clinic = new Clinic();

        clinic.setClinicName(clinicDto.clinicName());
        clinic.setAddress(clinicDto.address());
        clinic.setPhoneNumber(clinicDto.phoneNumber());

        Clinic saved = clinicRepository.save(clinic);

        return saved.getId();
    }

    @Override
    public Clinic getClinic(Long id) {
        Objects.requireNonNull(id, "id is null");

        return clinicRepository.findById(id)
                .orElseThrow(() -> new ClinicNotFoundException("Clinic with id " + id + " not found"));
    }

}
