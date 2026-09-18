package com.skr1l.minierp.controller;

import com.skr1l.minierp.dto.ProcedureRequestDto;
import com.skr1l.minierp.entity.Procedure;
import com.skr1l.minierp.service.ProcedureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/procedures")
public class ProcedureController {

    private final ProcedureService procedureService;

    @GetMapping("/{procedureId}")
    public Procedure getProcedureById(@PathVariable Long procedureId){

        return procedureService.getProcedureByProcedureId(procedureId);
    }

    @PostMapping("/{clinicId}/procedures")
    public void addProcedureToClinic(@Valid @RequestBody ProcedureRequestDto procedureDto,
                                     @PathVariable Long clinicId){
        procedureService.clinicAddProcedure(procedureDto, clinicId);
    }

    @PatchMapping("/{procedureId}")
    public void updateProcedure(@Valid @RequestBody ProcedureRequestDto procedureDto,
                                @PathVariable Long procedureId){
        procedureService.updateProcedure(procedureDto, procedureId);
    }

    @GetMapping("/{clinicId}/procedures")
    public List<Procedure> getAllProceduresByClinicId(@PathVariable Long clinicId){
        return procedureService.getProcedureByClinicId(clinicId);
    }

    @PatchMapping("/{procedureId}/deactivate")
    public void deactivateProcedure(@PathVariable Long procedureId){
        procedureService.deactivateProcedure(procedureId);
    }
}
