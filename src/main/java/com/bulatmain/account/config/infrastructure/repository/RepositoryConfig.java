package com.bulatmain.account.config.infrastructure.repository;

import com.bulatmain.account.application.port.gateway.UserGateway;
import com.bulatmain.account.infrastructure.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {
    @Bean
    public UserGateway userGateway(UserRepository userRepository) {
        return userRepository;
    }
}
