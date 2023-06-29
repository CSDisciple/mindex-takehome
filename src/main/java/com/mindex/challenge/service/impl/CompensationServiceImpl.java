package com.mindex.challenge.service.impl;


import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);
    @Autowired
    private CompensationRepository compRepository;

    @Override
    //ToDo implement create method
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation for employee id [{}]", compensation.getEmployee().getEmployeeId());
        compRepository.insert(compensation);
        return compensation;
    }

    @Override
    public Compensation read(String id){
        return compRepository.findCompensationByEmployee_EmployeeId(id);
    }

    @Override
    public List<Compensation> readAll(){
        return compRepository.findAll();
    }
}
