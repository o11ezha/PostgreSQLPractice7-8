package com.project.PostgreSQLPractice78.services;

import com.project.PostgreSQLPractice78.mainEntities.Client;
import com.project.PostgreSQLPractice78.repos.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepo clientRepo;

    public List<Client> findAllClients(){
        return clientRepo.findAll();
    }
}
