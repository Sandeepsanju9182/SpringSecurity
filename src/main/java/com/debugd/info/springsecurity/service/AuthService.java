package com.debugd.info.springsecurity.service;

import com.debugd.info.springsecurity.dto.UserRegisterRequestDTO;
import com.debugd.info.springsecurity.dto.UserRegisterResponseDTO;
import com.debugd.info.springsecurity.entity.User;
import com.debugd.info.springsecurity.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserRegisterResponseDTO register(UserRegisterRequestDTO registerRequestDTO){

        User user = new User();
        user.setUsername(registerRequestDTO.getUsername());

        String encodedPassword = passwordEncoder.encode(registerRequestDTO.getPassword());

        user.setPassword(encodedPassword);
        user.setEnabled(true);

        userRepository.save(user);

        UserRegisterResponseDTO responseDTO = new UserRegisterResponseDTO();

        responseDTO.setUsername(user.getUsername());
        responseDTO.setMessage("User Registered Successfully");

        return responseDTO;
    }
    public Boolean login(UserRegisterRequestDTO registerRequestDTO) {
        Optional<User> userOptional = userRepository.findByUsername(
                registerRequestDTO.getUsername());

        User user = userOptional.get();

        String encodedPassword = user.getPassword();

        return passwordEncoder.matches(
                registerRequestDTO.getPassword(),
                encodedPassword
        );
    }


}
