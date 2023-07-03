package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationImplTest {

    private String compensationCreateUrl;
    private String compensationReadUrl;

    @Autowired
    private CompensationService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String employeeUrl;
    private String employeeIdUrl;

    @Before
    public void setup() {
        compensationCreateUrl = "http://localhost:" + port + "/compensation";
        compensationReadUrl = "http://localhost:" + port + "/compensation/{id}";
        employeeUrl = "http://localhost:" + port + "/employee";
        employeeIdUrl = "http://localhost:" + port + "/employee/{id}";
    }

    @Test
    public void testCreateRead() {
        Compensation comp = new Compensation();
        Employee testEmployee = new Employee();
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");


        Employee createdEmployee = restTemplate.postForEntity(employeeUrl, testEmployee, Employee.class).getBody();

        comp.setEmployee(createdEmployee);
        comp.setSalary(new BigDecimal(100000));
        comp.setEffectiveDate(LocalDate.of(2020, 1, 8));

        // Create checks
        Compensation createdCompensation = restTemplate.postForEntity(compensationCreateUrl, comp, Compensation.class).getBody();
        assert createdCompensation != null;
        assertNotNull(createdCompensation.getEmployee().getEmployeeId());
        assertCompEquivalence(comp, createdCompensation);


        // Read checks
        Compensation readComp = restTemplate.getForEntity(compensationReadUrl, Compensation.class, createdCompensation.getEmployee().getEmployeeId()).getBody();
        assertEquals(createdCompensation.getEmployee().getEmployeeId(), readComp.getEmployee().getEmployeeId());
        assertCompEquivalence(createdCompensation, readComp);

    }

    private static void assertCompEquivalence(Compensation expected, Compensation actual) {
        assertEquals(expected.getEmployee().getEmployeeId(), actual.getEmployee().getEmployeeId());
        assertEquals(expected.getSalary(), actual.getSalary());
        assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
    }
}
