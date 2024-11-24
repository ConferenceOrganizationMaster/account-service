package com.bulatmain.account.config;

import com.bulatmain.account.application.model.value.fabric.*;
import com.bulatmain.account.application.model.value.validator.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class FabricConfig {
    @Bean
    public EmailFabric emailFabric(EmailValidator emailValidator) {
        return new EmailFabric(emailValidator);
    }

    @Bean
    public LoginFabric loginFabric(LoginValidator loginValidator) {
        return new LoginFabric(loginValidator);
    }

    @Bean
    public NameFabric nameFabric(NameValidator nameValidator) {
        return new NameFabric(nameValidator);
    }

    @Bean
    public PasswordFabric passwordFabric(
            PasswordValidator passwordValidator,
            PasswordEncoder passwordEncoder) {
        return new PasswordFabric(passwordValidator, passwordEncoder);
    }

    @Bean
    public PhoneFabric phoneFabric(PhoneValidator phoneValidator) {
        return new PhoneFabric(phoneValidator);
    }

    @Bean
    public UserFabric userFabric(
            UserIdFabric userIdFabric,
            LoginFabric loginFabric,
            PasswordFabric passwordFabric,
            NameFabric nameFabric,
            PhoneFabric phoneFabric
    ) {
        return new UserFabric(
                userIdFabric,
                loginFabric,
                passwordFabric,
                nameFabric,
                phoneFabric
        );
    }

    @Bean
    public UserDetailFabric userDetailFabric(
            NameFabric nameFabric,
            PhoneFabric phoneFabric
    ) {
        return new UserDetailFabric(
                nameFabric,
                phoneFabric
        );
    }

    @Bean
    public UserIdFabric userIdFabric(EmailFabric emailFabric) {
        return new UserIdFabric(emailFabric);
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
