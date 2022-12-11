package com.project.PostgreSQLPractice78.services;

import com.project.PostgreSQLPractice78.config.AccountDetails;
import com.project.PostgreSQLPractice78.mainEntities.AccountInfo;
import com.project.PostgreSQLPractice78.mainEntities.Worker;
import com.project.PostgreSQLPractice78.repos.AccountInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountInfoRepo accountInfoRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AccountInfo account = accountInfoRepo.findBylogin(username);
        if (account == null)
            throw new UsernameNotFoundException("User not found!");

        return new AccountDetails(account);
    }

    public List<AccountInfo> findAll() {
        return accountInfoRepo.findAll();
    }

    public AccountInfo findBylogin(String username) {
        return accountInfoRepo.findBylogin(username);
    }

    @Transactional
    public void createAccount(AccountInfo account) {
        accountInfoRepo.addUser(account.getLogin(), account.getHashPassword(), account.getWorkerCode().getWorkerCode(), null);

    }

    @Transactional
    public void saveUser(String username, String workerCode, String roles, String password) {

        Integer role = -1;

        switch (roles) {
            case "manager":
                role = 1;
            case "employee":
                role = 2;
        }

        accountInfoRepo.editAccount(workerCode,null, role, username, password );

    }

    public List<String> findAllLogins(){
        return accountInfoRepo.findAllLogins();
    }
}
