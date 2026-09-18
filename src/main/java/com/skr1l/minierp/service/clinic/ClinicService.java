package com.skr1l.minierp.service.clinic;

import com.skr1l.minierp.dto.ClinicRequestDto;
import com.skr1l.minierp.entity.Clinic;

public interface ClinicService {

    Long createClinic(ClinicRequestDto clinicDto);

    Clinic getClinic(Long id);

}
