package com.project.PostgreSQLPractice78.services;

import com.project.PostgreSQLPractice78.DTO.TaskDTO;
import com.project.PostgreSQLPractice78.DTO.WorkerDTO;
import com.project.PostgreSQLPractice78.mainEntities.Task;
import com.project.PostgreSQLPractice78.mainEntities.Worker;
import com.project.PostgreSQLPractice78.mainEntities.WorkerRole;
import com.project.PostgreSQLPractice78.repos.WorkerRepo;
import com.project.PostgreSQLPractice78.repos.WorkerRoleRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkerService {

    @Autowired
    WorkerRepo workerRepo;
    @Autowired
    WorkerRoleRepo workerRoleRepo;

    public Worker convertDTOToWorker(WorkerDTO workerDTO){
        ModelMapper model = new ModelMapper();

        model.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        Long telephone = Long.parseLong(workerDTO.getWorkerTelephone());
        WorkerRole workerRole = workerRoleRepo.findAllByRoleId(workerDTO.getRoleID());

        Worker worker = model.map(workerDTO, Worker.class);

        worker.setWorkerTelephone(telephone);
        worker.setRoleID(workerRole);
        return worker;
    }

    public WorkerDTO convertWorkerToDTO(String workerCode) {
        ModelMapper model = new ModelMapper();

        model.getConfiguration().setAmbiguityIgnored(true);
        return model.map(workerRepo.findByWorkerCode(workerCode), WorkerDTO.class);
    }

    public void createWorker(WorkerDTO workerDTO) {
        Worker worker = convertDTOToWorker(workerDTO);

        workerRepo.addWorker(worker.getWorkerCode(), worker.getRoleID().getRoleId(), worker.getWorkerName(),
                worker.getWorkerTelephone(), worker.getWorkerEmail(), null);

    }

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

    public List<String> findAllWorkerCodes(){
        return workerRepo.findAllWorkerCodes();
    }

    public List<String> findAllEmails(){return workerRepo.findAllEmails();
    }
}
