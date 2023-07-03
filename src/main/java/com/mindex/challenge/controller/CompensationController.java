package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);
    @Autowired
    CompensationService compService;

    @PostMapping("/compensation")
    public Compensation create(@RequestBody Compensation compensation){
        LOG.debug("Received a create compensation request for id [{}]", compensation.getEmployee().getEmployeeId());
        compensation = compService.create(compensation);
        return compensation;
    }

    @GetMapping("/compensation-list")
    public List<Compensation> read(){
        LOG.debug("Received a read compensation request for all employees");
        return compService.readAll();
    }


    @GetMapping("/compensation/{id}")
    public Compensation read(@PathVariable String id){
        LOG.debug("Received a read compensation request for id [{}]", id);
        return compService.read(id);
    }
}
