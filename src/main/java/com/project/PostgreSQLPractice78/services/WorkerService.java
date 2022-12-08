package com.project.PostgreSQLPractice78.services;

import com.project.PostgreSQLPractice78.mainEntities.Worker;
import com.project.PostgreSQLPractice78.repos.WorkerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Worker> findAllAuthors() {
        List<String> allAuthorsCodes = workerRepo.findAllAuthorsCodes();
        List<Worker> allAuthors = new ArrayList<Worker>();
        for (String author:allAuthorsCodes) {
            allAuthors.add(workerRepo.findByWorkerCode(author));
        }
        return allAuthors;
    }
}
