package com.example.securitypermission.controller;

import com.example.securitypermission.aop.CheckPermission;
import com.example.securitypermission.entity.Role;
import com.example.securitypermission.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    RoleRepository roleRepository;

//    @PreAuthorize(value = "hasAuthority('ADD_ROLE')")
    @CheckPermission(value = "ADD_ROLE")
    @PostMapping
    public HttpEntity<?> save(@RequestBody Role role) {
        roleRepository.save(new Role(
                role.getName(),
                role.getPermissions()
        ));
        return ResponseEntity.ok("Saved Lavozim!");
    }
}
