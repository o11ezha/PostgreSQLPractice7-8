package com.project.PostgreSQLPractice78.services;

import com.project.PostgreSQLPractice78.mainEntities.Worker;
import com.project.PostgreSQLPractice78.repos.WorkerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {

    @Autowired
    WorkerRepo workerRepo;

    public Worker findWorkerByWorkerCode(String workerCode){
        return workerRepo.findByWorkerCode(workerCode);
    }

    public List<Worker> findAll() {
        return workerRepo.findAll();
    }
}
