package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);
    @Autowired
    CompensationService compService;

    @PostMapping("/compensation")
    public ResponseEntity<Compensation> create(@RequestBody Compensation compensation){
        LOG.debug("Received a create compensation request for id [{}]", compensation.getEmployee().getEmployeeId());
        compService.create(compensation);
        return new ResponseEntity<>(compensation, HttpStatus.CREATED);
    }

    @GetMapping("/compensation")
    public ResponseEntity<List<Compensation>> read(){
        LOG.debug("Received a read compensation request for all employees");
        List<Compensation> allEmployeeComp = compService.readAll();
        return new ResponseEntity<>(compService.readAll(), HttpStatus.FOUND);
    }


    @GetMapping("/compensation/{id}")
    public ResponseEntity<Compensation> read(@PathVariable String id){
        LOG.debug("Received a read compensation request for id [{}]", id);
        Compensation comp = compService.read(id);
        return new ResponseEntity<>(comp, HttpStatus.FOUND);
    }
}
