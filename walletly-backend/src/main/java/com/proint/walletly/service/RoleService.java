package com.proint.walletly.service;

import com.proint.walletly.model.Role;
import com.proint.walletly.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    
    private final RoleRepository roleRepository;
    
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    public Role findByName(String name) {
        return roleRepository.findByNome(name)
                .orElseThrow(() -> new RuntimeException("Role not found: " + name));
    }
    
    public Role createRoleIfNotExists(String name) {
        Optional<Role> existingRole = roleRepository.findByNome(name);
        if (existingRole.isPresent()) {
            return existingRole.get();
        }
        
        Role role = new Role(name);
        return roleRepository.save(role);
    }
}
