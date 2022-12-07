package com.project.PostgreSQLPractice78.mainEntities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="task")
public class Task {
    @Id
    private Integer taskCode;
    @ManyToOne
    @JoinColumn(name = "contract_code", referencedColumnName = "contract_code")
    private Contract contractCode;
    @ManyToOne
    @JoinColumn(name = "author_code", referencedColumnName = "worker_code")
    private Worker authorCode;
    private String workerCode;
    private Integer taskPriority;
    private Boolean taskStatus;
    private String taskDescription;
    private Date addDate;
    private Date activationDate;
    private Date expirationDate;
    private Date completeDate;

    public Integer getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(int taskCode) {
        this.taskCode = taskCode;
    }

    public Contract getContractCode() {
        return contractCode;
    }

    public void setContractCode(Contract taskContractCode) {
        this.contractCode = taskContractCode;
    }

    public Worker getAuthorCode() {
        return authorCode;
    }

    public void setAuthorCode(Worker taskAuthorCode) {
        this.authorCode = taskAuthorCode;
    }

    public String getWorkerCode() {
        return workerCode;
    }

    public void setWorkerCode(String taskWorkerCode) {
        this.workerCode = taskWorkerCode;
    }

    public Integer getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(Integer taskTaskPriority) {
        this.taskPriority = taskTaskPriority;
    }

    public Boolean getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Boolean taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addTaskDate) {
        this.addDate = addTaskDate;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }
}
