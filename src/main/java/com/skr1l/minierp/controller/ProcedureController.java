package com.skr1l.minierp.controller;

import com.skr1l.minierp.entity.Procedure;
import com.skr1l.minierp.service.ProcedureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/procedures")
public class ProcedureController {

    private final ProcedureService procedureService;

    @GetMapping("/{procedureId)")
    public Procedure getProcedureById(@PathVariable Long procedureId){

        return procedureService.getProcedureByProcedureId(procedureId);
    }

    @PutMapping("/{procedureId}")
    public


}
