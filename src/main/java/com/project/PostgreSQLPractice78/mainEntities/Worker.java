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
}
