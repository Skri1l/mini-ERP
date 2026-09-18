package com.skr1l.minierp.service.procedure;

import com.skr1l.minierp.dto.ProcedureRequestDto;
import com.skr1l.minierp.entity.Procedure;

import java.util.List;

public interface ProcedureService {

    void clinicAddProcedure(ProcedureRequestDto procedureDto, Long clinicId);

    void deactivateProcedure(Long procedureId);

    void updateProcedure(ProcedureRequestDto procedureDto, Long procedureId);

    List<Procedure> getProcedureByClinicId(Long clinicId);

    Procedure getProcedureByProcedureId(Long procedureId);
}
