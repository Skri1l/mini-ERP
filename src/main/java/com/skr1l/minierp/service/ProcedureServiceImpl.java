package com.skr1l.minierp.service;

import com.skr1l.minierp.entity.Clinic;
import com.skr1l.minierp.entity.Procedure;
import com.skr1l.minierp.exception.ClinicNotFoundException;
import com.skr1l.minierp.exception.ProcedureNotFoundException;
import com.skr1l.minierp.repository.ClinicRepository;
import com.skr1l.minierp.repository.ProcedureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ProcedureServiceImpl implements ProcedureService {

    private ClinicRepository clinicRepository;

    private ProcedureRepository procedureRepository;

    @Override
    @Transactional
    public void clinicAddProcedure(Procedure procedure, Long clinicId) {

        Objects.requireNonNull(procedure, "procedure is null");
        Objects.requireNonNull(clinicId, "clinicId is null");

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
    public void updateProcedure(Procedure procedure) {
        Objects.requireNonNull(procedure, "procedure is null");

        Procedure updatedProcedure = procedureRepository.findById(procedure.getId())
                .orElseThrow(() -> new ProcedureNotFoundException(
                        "Procedure with id " + procedure.getId() +" not found"));

        updatedProcedure.setProcedureName(procedure.getProcedureName());
        updatedProcedure.setActive(procedure.isActive());
        updatedProcedure.setDescription(procedure.getDescription());
        updatedProcedure.setPrice(procedure.getPrice());
        updatedProcedure.setDurationMinutes(procedure.getDurationMinutes());

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
