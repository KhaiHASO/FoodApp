package net.javaguides.springboot.controller;

import java.util.List;

import net.javaguides.springboot.model.Role;
import net.javaguides.springboot.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // create role REST API
    @PostMapping
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        return new ResponseEntity<>(roleService.saveRole(role), HttpStatus.CREATED);
    }

    // get all roles REST API
    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    // get role by id REST API
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable("id") int roleId) {
        return new ResponseEntity<>(roleService.getRoleById(roleId), HttpStatus.OK);
    }

    // update role REST API
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable("id") int id, @RequestBody Role role) {
        return new ResponseEntity<>(roleService.updateRole(role, id), HttpStatus.OK);
    }

    // delete role REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") int id) {
        roleService.deleteRole(id);
        return new ResponseEntity<>("Role deleted successfully!.", HttpStatus.OK);
    }
}
