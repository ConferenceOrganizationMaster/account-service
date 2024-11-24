package com.bulatmain.account.config.application;

import com.bulatmain.account.application.model.value.validator.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorConfig {
    @Bean
    public EmailValidator emailValidator() {
        return new EmailValidator();
    }

    @Bean
    public LoginValidator loginValidator() {
        return new LoginValidator();
    }

    @Bean
    public PasswordValidator passwordValidator() {
        return new PasswordValidator();
    }

    @Bean
    public NameValidator nameValidator() {
        return new NameValidator();
    }

    @Bean
    public PhoneValidator phoneValidator() {
        return new PhoneValidator();
    }
}
