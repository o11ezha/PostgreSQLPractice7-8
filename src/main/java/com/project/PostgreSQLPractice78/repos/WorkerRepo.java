package com.project.PostgreSQLPractice78.repos;

import com.project.PostgreSQLPractice78.mainEntities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkerRepo extends JpaRepository<Worker, String> {
    Worker findByWorkerCode(String workercode);

    @Query(value = "SELECT worker_code FROM worker WHERE role_id = 1", nativeQuery = true)
    List<String> findAllAuthorsCodes();
}
