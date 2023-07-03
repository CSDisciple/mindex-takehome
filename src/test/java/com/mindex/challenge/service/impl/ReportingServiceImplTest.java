package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingServiceImplTest {

    private String reportingIdUrl;
    private String employeeUrl;

    @Autowired
    private ReportingService reportingService;

    @Autowired
    private EmployeeService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        reportingIdUrl = "http://localhost:" + port + "/report/{id}";
        employeeUrl = "http://localhost:" + port + "/employee";
    }


    //ToDo implement
    @Test
    @DisplayName("Test read Reporting Structure given employeeId")
    public void testRead() {
        ResponseEntity<List<Employee>> response = restTemplate.exchange(employeeUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
        });

        List<Employee> emp = response.getBody();
        // Read checks
        ReportingStructure readReports = restTemplate.getForEntity(reportingIdUrl, ReportingStructure.class, emp.get(0).getEmployeeId()).getBody();
        assertNotNull(emp);
        assertNotNull(readReports);
        assertEquals(readReports.getEmployee().getEmployeeId(), emp.get(0).getEmployeeId());
    }

    //ToDo implement
    @Test
    @DisplayName("Test read Reporting Structure given employeeId with direct report list as null")
    public void testReadEmployeeWithNoReports() {
        ResponseEntity<List<Employee>> response = restTemplate.exchange(employeeUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
        });

        List<Employee> emp = response.getBody();

        assertNotNull(emp);


        List<Employee> empNoReports = emp.stream()
                .filter(employee -> employee.getDirectReports() == null)
                .collect(Collectors.toList());
        // Read checks
        ReportingStructure readReports = restTemplate.getForEntity(reportingIdUrl, ReportingStructure.class, empNoReports.get(0).getEmployeeId()).getBody();

        assertNotNull(readReports);
        assertEquals(readReports.getEmployee().getEmployeeId(), empNoReports.get(0).getEmployeeId());
        assertNull(empNoReports.get(0).getDirectReports());
    }

}
