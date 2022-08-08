package com.sprint2.model;

import javax.persistence.*;

@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userRoleId;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "roleId")
    private Roles roles;

    public UserRole() {
    }

    public UserRole(Users users, Roles roles) {
        this.users = users;
        this.roles = roles;
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }


}
