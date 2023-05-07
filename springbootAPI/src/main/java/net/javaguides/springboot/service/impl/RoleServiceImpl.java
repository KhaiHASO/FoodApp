package net.javaguides.springboot.service.impl;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Role;
import net.javaguides.springboot.repository.RoleRepository;
import net.javaguides.springboot.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        super();
        this.roleRepository = roleRepository;
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(int id) {
        return roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role", "Id", id));
    }

    @Override
    public Role updateRole(Role role, int id) {
        Role existingRole = getRoleById(id);

        existingRole.setRoleName(role.getRoleName());

        return roleRepository.save(existingRole);
    }

    @Override
    public void deleteRole(int id) {
        Role existingRole = getRoleById(id);
        roleRepository.delete(existingRole);
    }
}
