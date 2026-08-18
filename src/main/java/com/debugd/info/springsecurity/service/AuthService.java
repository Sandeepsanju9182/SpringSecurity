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

    public AuthService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserRegisterResponseDTO register(
            UserRegisterRequestDTO registerRequestDto) {

        User user = new User();
        user.setUsername(registerRequestDto.getUsername());

        String encodedPassword =
                passwordEncoder.encode(registerRequestDto.getPassword());

        user.setPassword(encodedPassword);
        user.setEnabled(true);

        Role role = roleRepository.findByName("ROLE_USER").get();

        user.getRoles().add(role);

        userRepository.save(user);

        UserRegisterResponseDTO responseDto = new
                UserRegisterResponseDTO();

        responseDto.setUsername(user.getUsername());
        responseDto.setMessage("User saved Successfully");

        return responseDto;

    }
}
