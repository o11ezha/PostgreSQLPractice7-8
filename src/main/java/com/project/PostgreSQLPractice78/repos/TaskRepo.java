package com.project.PostgreSQLPractice78.repos;

import com.project.PostgreSQLPractice78.mainEntities.Task;
import com.project.PostgreSQLPractice78.mainEntities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


public interface TaskRepo extends JpaRepository<Task, Integer> {
    Task findByTaskCode(Integer taskCode);
    List<Task> findAllByAuthorCode(Worker authorCode);
    List<Task> findAllByWorkerCode(String workerCode);

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

    @Query(value = "CALL edit_task_status(:complete_task_code, :resultproc);", nativeQuery = true)
    Boolean editTaskStatus(@Param("complete_task_code") Integer taskCode,
                    @Param("resultproc") Boolean resultProc);

    @Query(value = "CALL edit_task(:old_task_code, :resultproc, :new_contract_code, :new_author_code,:new_worker_code,:new_task_priority, :new_task_description, :new_expiration_date);", nativeQuery = true)
    Boolean editTask(@Param("old_task_code") Integer taskCode,
                           @Param("resultproc") Boolean newResultProc,
                           @Param("new_contract_code") Integer newContractCode,
                           @Param("new_author_code") String newAuthorCode,
                           @Param("new_worker_code") String newWorkerCode,
                           @Param("new_task_priority") Integer newTaskPriority,
                           @Param("new_task_description") String newTaskDescription,
                           @Param("new_expiration_date") Date newExpirationDate);
}
