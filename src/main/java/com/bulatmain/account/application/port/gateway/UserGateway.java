package com.bulatmain.account.application.port.gateway;

import com.bulatmain.account.application.model.dto.UserDTO;

public interface UserGateway {
    boolean exists(String id);

    String save(UserDTO userDTO);
}
