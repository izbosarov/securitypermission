package com.example.securitypermission.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.securitypermission.entity.Role;
import com.example.securitypermission.entity.User;
import com.example.securitypermission.entity.enums.Permission;
import com.example.securitypermission.repository.RoleRepository;
import com.example.securitypermission.repository.UserRepository;
import com.example.securitypermission.utils.AppConstants;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class DataLoader implements CommandLineRunner {

    @Value("${spring.sql.init.mode}")
    private String initMode;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {

        if (initMode.equals("always")) {

            Permission[] values = Permission.values();

            //admin roli bazaga yozish
            Role admin = roleRepository.save(new Role(
                    AppConstants.ADMIN,
                    new HashSet<>(Arrays.asList(values))
            ));
            Role user = roleRepository.save(new Role(
                    AppConstants.USER,
                    new HashSet<>(Arrays.asList(Permission.READ_CATEGORY,
                            Permission.READ_ONE_CATEGORY))
            ));

            userRepository.save(new User(
                    "Admin",
                    "admin",
                    passwordEncoder.encode("admin"),
                    admin,
                    true
            ));
            userRepository.save(new User(
                    "User",
                    "user",
                    passwordEncoder.encode("user"),
                    user,
                    true
            ));
        }
    }
}
