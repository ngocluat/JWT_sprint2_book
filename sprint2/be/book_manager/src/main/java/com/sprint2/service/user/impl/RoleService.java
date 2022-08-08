package com.sprint2.service.user.impl;

import com.sprint2.model.Roles;
import com.sprint2.repository.user.IRoleRepository;
import com.sprint2.service.user.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository iRoleRepository;

    @Override
    public Roles findRoleByName(String roleUser) {
        return this.iRoleRepository.findRolesByRoleName(roleUser);

    }
}
