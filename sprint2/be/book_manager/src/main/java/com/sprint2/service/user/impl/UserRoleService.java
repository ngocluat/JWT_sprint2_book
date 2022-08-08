package com.sprint2.service.user.impl;

import com.sprint2.model.UserRole;
import com.sprint2.repository.user.IUserRoleRepository;
import com.sprint2.service.user.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService implements IUserRoleService {
    @Autowired
    private IUserRoleRepository iUserRoleRepository;

    @Override
    public UserRole findUserRole(String roleName) {
        return this.iUserRoleRepository.findUserRoleByUsername(roleName);
    }

    @Override
    public void saveUserRole(UserRole userRole) {
        this.iUserRoleRepository.save(userRole);
    }

}
