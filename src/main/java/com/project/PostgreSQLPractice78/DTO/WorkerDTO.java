package com.project.PostgreSQLPractice78.DTO;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class WorkerDTO {

    @NotNull(message = "Введите код работника")
    @Pattern(regexp = "^[a-zA-Z0-9]{6}+$", message = "Введите корректный код работника (6 символов, латиница+цифры)")
    private String workerCode;
    @NotNull(message = "Выберите роль")
    private Integer roleID;
    @NotNull(message = "Введите имя работника")
    @Pattern(regexp = "^[ЁёА-я]+\\s[ЁёА-я]+$", message ="ФИ! Только на кириллице")
    private String workerName;
    @NotNull(message = "Введите телефон работника")
    @Pattern(regexp="^[8][0-9]{10}+$", message = "Для ввода доступны только цифры (8NNNNNNNNNN)")
    private String workerTelephone;
    @NotNull(message = "Введите почту работника")
    @Email(message = "Введите почту корректно")
    private String workerEmail;


    public String getWorkerCode() {
        return workerCode;
    }

    public void setWorkerCode(String workerCode) {
        this.workerCode = workerCode;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerTelephone() {
        return workerTelephone;
    }

    public void setWorkerTelephone(String workerTelephone) {
        this.workerTelephone = workerTelephone;
    }

    public String getWorkerEmail() {
        return workerEmail;
    }

    public void setWorkerEmail(String workerEmail) {
        this.workerEmail = workerEmail;
    }
}
