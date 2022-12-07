package com.project.PostgreSQLPractice78.repos;

import com.project.PostgreSQLPractice78.mainEntities.WorkerRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRoleRepo extends JpaRepository<WorkerRole, Integer> {
}
