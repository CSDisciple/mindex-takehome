package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportingController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    ReportingService reportService;


    @GetMapping("/report/{id}")
    public ReportingStructure report(@PathVariable String id){
        LOG.debug("Received report structure read request for id [{}]", id);
        return reportService.read(id);
    }

    @GetMapping("/report/diagram/{id}")
    public ReportingStructure reportDiagram(@PathVariable String id){
        LOG.debug("Received report structure read request for id [{}]", id);
        return reportService.readDiagram(id);
    }
}
