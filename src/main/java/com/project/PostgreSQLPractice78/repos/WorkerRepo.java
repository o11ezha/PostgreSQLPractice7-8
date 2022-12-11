package com.project.PostgreSQLPractice78.repos;

import com.project.PostgreSQLPractice78.mainEntities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkerRepo extends JpaRepository<Worker, String> {
    Worker findByWorkerCode(String workercode);

    @Query(value = "SELECT worker_code FROM worker WHERE role_id = 1", nativeQuery = true)
    List<String> findAllAuthorsCodes();

    @Query(value = "SELECT worker_code FROM worker", nativeQuery = true)
    List<String> findAllWorkerCodes();

    @Query(value = "SELECT worker_email FROM worker", nativeQuery = true)
    List<String> findAllEmails();

    @Query(value = "CALL add_worker(:new_worker_code,:new_role_id, :new_worker_name, :new_worker_telephone, :new_worker_email,  :resultproc);", nativeQuery = true)
    Boolean addWorker(@Param("new_worker_code") String workerCode,
                           @Param("new_role_id") Integer roleID,
                           @Param("new_worker_name") String workerName,
                           @Param("new_worker_telephone") Long workerTelephone,
                           @Param("new_worker_email") String email,
                           @Param("resultproc") Boolean resultProc);
}
