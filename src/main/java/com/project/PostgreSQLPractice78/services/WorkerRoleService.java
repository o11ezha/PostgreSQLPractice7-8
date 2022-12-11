package com.project.PostgreSQLPractice78.services;

import com.project.PostgreSQLPractice78.mainEntities.WorkerRole;
import com.project.PostgreSQLPractice78.repos.WorkerRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerRoleService {

    @Autowired
    WorkerRoleRepo workerRoleRepo;

    public List<WorkerRole> findAllRoles(){
        return workerRoleRepo.findAllRoles();
    }

}
