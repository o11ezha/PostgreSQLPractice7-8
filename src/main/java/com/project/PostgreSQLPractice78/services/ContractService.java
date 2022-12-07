package com.project.PostgreSQLPractice78.services;

import com.project.PostgreSQLPractice78.mainEntities.AccountInfo;
import com.project.PostgreSQLPractice78.mainEntities.Contract;
import com.project.PostgreSQLPractice78.repos.ContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {

    @Autowired
    ContractRepo contractRepo;

    public List<Contract> findAll() {
        return contractRepo.findAll();
    }
}
