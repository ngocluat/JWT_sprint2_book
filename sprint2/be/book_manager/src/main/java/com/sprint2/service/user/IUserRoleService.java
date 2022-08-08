package com.sprint2.service.user;


import com.sprint2.model.UserRole;

public interface IUserRoleService {
    UserRole findUserRole(String roleName);

    void saveUserRole(UserRole userRole);

}
