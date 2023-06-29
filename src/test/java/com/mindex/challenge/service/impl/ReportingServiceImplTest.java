package com.mindex.challenge.service.impl;

import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingServiceImplTest {

    private String reportingIdUrl;

    @Autowired
    private ReportingService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        reportingIdUrl = "http://localhost:" + port + "/report/{id}";
    }



    //ToDo implement
    @Test
    public void testRead(){

    }

    //ToDo implement
    @Test
    public void testReadEmployeeWithNoReports(){

    }


}
