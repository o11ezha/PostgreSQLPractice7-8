package com.project.PostgreSQLPractice78.mainEntities;

import javax.persistence.*;

@Entity
@Table (name="worker")
public class Worker {

    @Id
    @Column(name = "worker_code")
    private String workerCode;
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private WorkerRole roleID;
    private String workerName;
    private Long workerTelephone;
    private String workerEmail;

    public String getWorkerCode() {
        return workerCode;
    }

    public WorkerRole getRoleID() {
        return roleID;
    }

    public String getWorkerName() {
        return workerName;
    }

    public Long getWorkerTelephone() {
        return workerTelephone;
    }

    public String getWorkerEmail() {
        return workerEmail;
    }

    public void setWorkerCode(String workerCode) {
        this.workerCode = workerCode;
    }

    public void setRoleID(WorkerRole roleID) {
        this.roleID = roleID;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public void setWorkerTelephone(Long workerTelephone) {
        this.workerTelephone = workerTelephone;
    }

    public void setWorkerEmail(String workerEmail) {
        this.workerEmail = workerEmail;
    }
}
