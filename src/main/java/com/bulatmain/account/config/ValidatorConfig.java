package com.bulatmain.account.config;

import com.bulatmain.account.application.model.value.validator.EmailValidator;
import com.bulatmain.account.application.model.value.validator.LoginValidator;
import com.bulatmain.account.application.model.value.validator.PasswordValidator;
import com.bulatmain.account.application.model.value.validator.PhoneValidator;
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
    public PhoneValidator phoneValidator() {
        return new PhoneValidator();
    }
}
