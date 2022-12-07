package com.project.PostgreSQLPractice78.repos;

import com.project.PostgreSQLPractice78.mainEntities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


public interface TaskRepo extends JpaRepository<Task, Integer> {
    Task findByTaskCode(Integer taskCode);

    @Query (value = "SELECT task_code FROM task", nativeQuery = true)
    List<Integer> allTaskCodes();

    @Query(value = "CALL add_task(:task_code, :author_login, :executor_code, :task_priority, :task_description, :resultproc, :contract_code, :expiration_date);", nativeQuery = true)
    Boolean addTask(@Param("task_code") Integer taskCode,
                    @Param("author_login") String login,
                    @Param("executor_code") String taskWorkerCode,
                    @Param("task_priority") Integer taskPriority,
                    @Param("task_description") String taskDescription,
                    @Param("resultproc") Boolean resultProc,
                    @Param("contract_code") Integer taskContractCode,
                    @Param("expiration_date") Date expirationDate);
}
