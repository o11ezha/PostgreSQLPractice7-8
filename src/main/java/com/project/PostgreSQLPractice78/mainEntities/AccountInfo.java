package com.project.PostgreSQLPractice78.mainEntities;

import javax.persistence.*;

@Entity
@Table(name = "account_info")
public class AccountInfo {
    @Id
    private String login;
    private String hashPassword;
    @OneToOne
    @JoinColumn(name = "worker_code", referencedColumnName = "worker_code")
    private Worker workerCode;
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private WorkerRole roleID;

    public String getLogin() {
        return login;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public Worker getWorkerCode() {
        return workerCode;
    }

    public WorkerRole getRoleID() {
        return roleID;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public void setWorkerCode(Worker workerCode) {
        this.workerCode = workerCode;
    }

    public void setRoleID(WorkerRole roleID) {
        this.roleID = roleID;
    }
}
