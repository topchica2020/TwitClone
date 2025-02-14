package com.example.twitterclone.controller;

import com.example.twitterclone.model.Role;
import com.example.twitterclone.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) { this.roleService = roleService; }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() { return ResponseEntity.ok(roleService.getAllRoles()); }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) { return ResponseEntity.ok(roleService.saveRole(role)); }
}
