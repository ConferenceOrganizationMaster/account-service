package com.bulatmain.account.config;

import com.bulatmain.account.application.model.dto.UserDTO;
import com.bulatmain.account.application.port.gateway.UserGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class UserGatewayConfig {

    @Bean
    public UserGateway userGateway() {
        return new UserGatewayImpl();
    }

    @Slf4j
    static private class UserGatewayImpl implements UserGateway {
        Map<String, UserDTO> users;

        @Override
        public boolean exists(String id) {
            return users.containsKey(id);
        }

        @Override
        public String save(UserDTO userDTO) {
            users.put(userDTO.getId(), userDTO);
            return userDTO.getId();
        }
    }
}
