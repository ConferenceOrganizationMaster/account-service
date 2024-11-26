package com.bulatmain.account.application.port.request;

import lombok.*;

@Data
@Builder
public class RegisterUserRequest {
    private final String email;
    private final String login;
    private final String password;

}
