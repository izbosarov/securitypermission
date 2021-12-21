package com.example.securitypermission.service;

import com.example.securitypermission.repository.RoleRepository;
import com.example.securitypermission.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;
//                          code error
//    public ApiResponse registerUser(RegisterDto registerDto) {
//        if (userRepository.existsByUsername(registerDto.getUsername()))
//            return new ApiResponse("Username already exists!",false);
//
//        if (!registerDto.getPassword().equals(registerDto.getPrePassword()))
//            return new ApiResponse("Passwors are not compatible!",false);
//        User user = new User(
//                registerDto.getFullName(),
//                registerDto.getUsername(),
//                passwordEncoder.encode(registerDto.getPassword()),
//                roleRepository.findByName(AppConstants.USER).orElseThrow(() -> new ResourceNotFoundException("Role not found!","name",AppConstants.USER)),
//                true
//                );
//        userRepository.save(user);
//        return new ApiResponse("User saved!", true);
//    }
    //

    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUserName(username)
                .orElseThrow(()-> new UsernameNotFoundException(username));
    }
}
