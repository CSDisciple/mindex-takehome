package com.mindex.challenge.service.impl;

import com.mindex.challenge.controller.EmployeeController;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingServiceImpl implements ReportingService {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeService employeeService;
    @Override
    public ReportingStructure read(String id){
        ReportingStructure rs = new ReportingStructure();
        Employee emp = employeeService.read(id);

        LOG.debug("Reading reporting structure for employee id [{}]", emp.getEmployeeId());
        int numberOfReports = reportCount(emp);
        rs.setEmployee(emp);
        rs.setNumberOfReports(numberOfReports);

        return rs;
    }

    public int reportCount(Employee employee){
        if(employee.getDirectReports() == null){
            return 0;
        }

        int numberOfReports = employee.getDirectReports().size();

        for(Employee report: employee.getDirectReports()){
            Employee emp = employeeService.read(report.getEmployeeId());
            numberOfReports += emp.getDirectReports() != null ? emp.getDirectReports().size() : 0;
        }

        return numberOfReports;
    }
}
