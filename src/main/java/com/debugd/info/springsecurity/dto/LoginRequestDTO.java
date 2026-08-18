package com.debugd.info.springsecurity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {

    private String username;

    private String password;
}
