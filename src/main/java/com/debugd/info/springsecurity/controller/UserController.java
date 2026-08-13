package com.debugd.info.springsecurity.controller;

import com.debugd.info.springsecurity.dto.UserRegisterRequestDTO;
import com.debugd.info.springsecurity.dto.UserRegisterResponseDTO;
import com.debugd.info.springsecurity.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private AuthService authService;

    public UserController(AuthService authService){
        this.authService = authService;
    }

//    @Autowired
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/hello")
    public String Hello(){
//        System.out.println(passwordEncoder.encode("secret123"));
//        System.out.println(passwordEncoder.encode("secret123"));
//
//        System.out.println(passwordEncoder.matches("secret123",
//                "$2a$10$sDioJcUTRt/b/B92gyq1dexiczJrU4kdEDErzxNlgnZ3W0bGoFRSO"));
        return "Hello";
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDTO> register(@RequestBody UserRegisterRequestDTO registerRequestDTO){

        UserRegisterResponseDTO userRegisterResponseDTO = authService.register(registerRequestDTO);

        return ResponseEntity.ok(userRegisterResponseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(
            @RequestBody UserRegisterRequestDTO registerRequestDTO) {

        Boolean loggedIn = authService.login(registerRequestDTO);

        return ResponseEntity.ok(loggedIn);
    }

    @GetMapping("/token")
    public CsrfToken getToken(CsrfToken csrfToken){
        return csrfToken;
    }

}
