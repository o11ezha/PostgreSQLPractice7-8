package com.project.PostgreSQLPractice78.repos;

import com.project.PostgreSQLPractice78.mainEntities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, String> {
}
