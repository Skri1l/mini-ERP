package com.skr1l.minierp.service;

import com.skr1l.minierp.dto.ClinicRequestDto;
import com.skr1l.minierp.entity.Clinic;
import com.skr1l.minierp.entity.Procedure;

import java.util.List;

public interface ClinicService {

    Long createClinic(ClinicRequestDto clinicDto);

    Clinic getClinic(Long id);

    void clinicAddProcedure(Procedure procedure, Long clinicId);

    List<Procedure> getProcedureByClinicId(Long clinicId);

    Procedure getProcedureByProcedureId(Long procedureId);

    void updateProcedure(Procedure procedure);

    void deactivateProcedure(Long procedureId);
}
