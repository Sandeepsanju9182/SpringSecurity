package com.debugd.info.springsecurity.service;

import com.debugd.info.springsecurity.dto.UserRegisterRequestDTO;
import com.debugd.info.springsecurity.dto.UserRegisterResponseDTO;
import com.debugd.info.springsecurity.entity.Role;
import com.debugd.info.springsecurity.entity.User;
import com.debugd.info.springsecurity.repository.RoleRepository;
import com.debugd.info.springsecurity.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserRegisterResponseDTO register(UserRegisterRequestDTO registerRequestDTO){

        User user = new User();
        user.setUsername(registerRequestDTO.getUsername());

        String encodedPassword = passwordEncoder.encode(registerRequestDTO.getPassword());

        user.setPassword(encodedPassword);
        user.setEnabled(true);

        Role role = roleRepository.findByName("ROLE_USER").get();
        user.getRoles().add(role);

        userRepository.save(user);

        UserRegisterResponseDTO responseDTO = new UserRegisterResponseDTO();

        responseDTO.setUsername(user.getUsername());
        responseDTO.setMessage("User Registered Successfully");

        return responseDTO;
    }
}
