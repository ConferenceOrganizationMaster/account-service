package com.bulatmain.account.config;

import com.bulatmain.account.application.model.value.fabric.UserFabric;
import com.bulatmain.account.application.port.gateway.EventPublisher;
import com.bulatmain.account.application.port.gateway.UserGateway;
import com.bulatmain.account.application.usecase.RegisterUserUC;
import com.bulatmain.account.application.usecase.impl.RegisterUserUCImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    @Bean
    public RegisterUserUC registerUserUc(
            UserGateway userGateway,
            UserFabric  userFabric,
            EventPublisher eventPublisher
    ) {
        return new RegisterUserUCImpl(userGateway, userFabric, eventPublisher);
    }
}
