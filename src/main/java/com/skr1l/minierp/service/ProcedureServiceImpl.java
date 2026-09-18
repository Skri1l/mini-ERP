package com.skr1l.minierp.service;

import com.skr1l.minierp.dto.ProcedureRequestDto;
import com.skr1l.minierp.entity.Clinic;
import com.skr1l.minierp.entity.Procedure;
import com.skr1l.minierp.exception.ClinicNotFoundException;
import com.skr1l.minierp.exception.ProcedureNotFoundException;
import com.skr1l.minierp.repository.ClinicRepository;
import com.skr1l.minierp.repository.ProcedureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProcedureServiceImpl implements ProcedureService {

    private final ClinicRepository clinicRepository;

    private final ProcedureRepository procedureRepository;

    @Override
    @Transactional
    public void clinicAddProcedure(ProcedureRequestDto procedureDto, Long clinicId) {

        Objects.requireNonNull(procedureDto, "procedure is null");
        Objects.requireNonNull(clinicId, "clinicId is null");

        Procedure procedure = new Procedure();
        procedure.setProcedureName(procedureDto.procedureName());
        procedure.setDescription(procedureDto.description());
        procedure.setPrice(procedureDto.price());
        procedure.setDurationMinutes(procedureDto.durationMinutes());

        Clinic clinic = clinicRepository.findById(clinicId)
                .orElseThrow(() -> new ClinicNotFoundException(
                        "Clinic with id " + clinicId + " not found"));

        procedure.setClinic(clinic);

        procedureRepository.save(procedure);
    }

    @Override
    public List<Procedure> getProcedureByClinicId(Long clinicId) {

        Objects.requireNonNull(clinicId, "clinicId is null");

        Clinic clinic = clinicRepository.findById(clinicId)
                .orElseThrow(() -> new ClinicNotFoundException(
                        "Clinic with id " + clinicId + " not found"));

        return procedureRepository.findAllByClinicId(clinicId);
    }

    @Override
    public Procedure getProcedureByProcedureId(Long procedureId) {
        Objects.requireNonNull(procedureId, "procedureId is null");

        return procedureRepository.findById(procedureId)
                .orElseThrow(() -> new ProcedureNotFoundException(
                        "Procedure with id " + procedureId + " not found"));
    }

    @Override
    @Transactional
    public void updateProcedure(ProcedureRequestDto procedureDto, Long procedureId) {
        Objects.requireNonNull(procedureDto, "procedure is null");
        Objects.requireNonNull(procedureId, "procedureId is null");

        Procedure updatedProcedure = procedureRepository.findById(procedureId)
                .orElseThrow(() -> new ProcedureNotFoundException(
                        "Procedure with id " + procedureId +" not found"));

        updatedProcedure.setProcedureName(procedureDto.procedureName());
        updatedProcedure.setDescription(procedureDto.description());
        updatedProcedure.setPrice(procedureDto.price());
        updatedProcedure.setDurationMinutes(procedureDto.durationMinutes());

        procedureRepository.save(updatedProcedure);
    }

    @Override
    @Transactional
    public void deactivateProcedure(Long procedureId) {
        Objects.requireNonNull(procedureId, "procedureId is null");

        Procedure procedure = procedureRepository.findById(procedureId)
                .orElseThrow(() -> new ProcedureNotFoundException(
                        "Procedure with id " + procedureId +" not found"));
        procedure.setActive(false);
        procedureRepository.save(procedure);
    }
}
