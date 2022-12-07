package com.project.PostgreSQLPractice78.repos;

import com.project.PostgreSQLPractice78.mainEntities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepo extends JpaRepository<Worker, String> {
    Worker findByWorkerCode(String workercode);
}
