package com.debugd.info.springsecurity.service;

import com.debugd.info.springsecurity.entity.Role;
import com.debugd.info.springsecurity.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void addRole(Role role){
        roleRepository.save(role);
    }
}
