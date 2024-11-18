package com.bulatmain.account.application.port.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
public class RegisterUserRequest {
    private final String email;
    private final String login;
    private final String password;

}
