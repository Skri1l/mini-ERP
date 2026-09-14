package com.skr1l.minierp.repository;

import com.skr1l.minierp.entity.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, Long> {

    List<Procedure> findAllByClinicId(Long clinicId);
}
