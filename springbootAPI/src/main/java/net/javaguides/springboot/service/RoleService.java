package net.javaguides.springboot.service;

import java.util.List;

import net.javaguides.springboot.model.Role;

public interface RoleService {
    Role saveRole(Role role);
    List<Role> getAllRoles();
    Role getRoleById(int id);
    Role updateRole(Role role, int id);
    void deleteRole(int id);
}
