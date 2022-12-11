package com.project.PostgreSQLPractice78.mainEntities;

import javax.persistence.*;

@Entity
@Table (name = "worker_role")
public class WorkerRole {

    @Id
    @Column(name = "role_id")
    private Integer roleId;
    private String roleName;

    public Integer getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
