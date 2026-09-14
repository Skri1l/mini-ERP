package com.skr1l.minierp.service;

import com.skr1l.minierp.entity.Procedure;

import java.util.List;

public interface ProcedureService {

    void clinicAddProcedure(Procedure procedure, Long clinicId);

    void deactivateProcedure(Long procedureId);

    void updateProcedure(Procedure procedure);

    List<Procedure> getProcedureByClinicId(Long clinicId);

    Procedure getProcedureByProcedureId(Long procedureId);
}
