package com.project.PostgreSQLPractice78.repos;

import com.project.PostgreSQLPractice78.mainEntities.AccountInfo;
import com.project.PostgreSQLPractice78.mainEntities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountInfoRepo extends JpaRepository<AccountInfo, String> {
    AccountInfo findBylogin(String username);
    AccountInfo findByworkerCode(Worker workercode);
}
