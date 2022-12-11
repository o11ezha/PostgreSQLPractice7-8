package com.project.PostgreSQLPractice78.repos;

import com.project.PostgreSQLPractice78.mainEntities.AccountInfo;
import com.project.PostgreSQLPractice78.mainEntities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

public interface AccountInfoRepo extends JpaRepository<AccountInfo, String> {
    AccountInfo findBylogin(String username);
    AccountInfo findByworkerCode(Worker workercode);
    @Query (value = "SELECT login FROM account_info", nativeQuery = true)
    List<String> findAllLogins();

    @Transactional
    @Query(value = "CALL add_user(:user_login, :user_password, :user_code, :resultproc);", nativeQuery = true)
    Boolean addUser(@Param("user_login") String login,
                        @Param("user_password") String hashPassword,
                        @Param("user_code") String workerCode,
                        @Param("resultproc") Boolean resultProc);

    @Transactional
    @Query(value = "CALL edit_account(:account_code, :resultproc, :new_role, :new_login, :new_password);", nativeQuery = true)
    Boolean editAccount(@Param("account_code") String accountCode,
                           @Param("resultproc") Boolean resultProc,
                           @Param("new_role") Integer newRole,
                           @Param("new_login") String newLogin,
                           @Param("new_password") String newPassword);
}
