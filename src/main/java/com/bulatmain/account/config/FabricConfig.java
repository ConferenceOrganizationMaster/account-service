package com.bulatmain.account.config;

import com.bulatmain.account.application.model.value.fabric.*;
import com.bulatmain.account.application.model.value.validator.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class FabricConfig {
    @Bean
    EmailFabric emailFabric(EmailValidator emailValidator) {
        return new EmailFabric(emailValidator);
    }

    @Bean
    LoginFabric loginFabric(LoginValidator loginValidator) {
        return new LoginFabric(loginValidator);
    }

    @Bean
    NameFabric nameFabric(NameValidator nameValidator) {
        return new NameFabric(nameValidator);
    }

    @Bean
    PasswordFabric passwordFabric(
            PasswordValidator passwordValidator,
            PasswordEncoder passwordEncoder) {
        return new PasswordFabric(passwordValidator, passwordEncoder);
    }

    @Bean
    PhoneFabric phoneFabric(PhoneValidator phoneValidator) {
        return new PhoneFabric(phoneValidator);
    }

    @Bean
    UserIdFabric userIdFabric(EmailFabric emailFabric) {
        return new UserIdFabric(emailFabric);
    }



}
