package com.project.PostgreSQLPractice78.repos;

import com.project.PostgreSQLPractice78.mainEntities.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganisationRepo extends JpaRepository<Organisation, Integer> {
}
