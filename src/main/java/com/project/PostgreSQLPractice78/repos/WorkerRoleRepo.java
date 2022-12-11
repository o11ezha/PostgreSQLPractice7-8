package com.project.PostgreSQLPractice78.repos;

import com.project.PostgreSQLPractice78.mainEntities.WorkerRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkerRoleRepo extends JpaRepository<WorkerRole, Integer> {
    WorkerRole findAllByRoleId(Integer RoleID);

    @Query(value = "SELECT * FROM worker_role WHERE role_id != 0", nativeQuery = true)
    List<WorkerRole> findAllRoles();
}
