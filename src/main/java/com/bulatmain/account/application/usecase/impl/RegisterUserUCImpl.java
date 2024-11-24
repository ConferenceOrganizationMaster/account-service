package com.bulatmain.account.application.usecase.impl;

import com.bulatmain.account.application.model.dto.UserDTO;
import com.bulatmain.account.application.model.value.exception.InvalidLoginException;
import com.bulatmain.account.application.model.value.exception.InvalidPasswordException;
import com.bulatmain.account.application.model.value.exception.InvalidUserIdException;
import com.bulatmain.account.application.model.value.fabric.UserFabric;
import com.bulatmain.account.application.port.event.UserRegisteredEvent;
import com.bulatmain.account.application.port.gateway.EventPublisher;
import com.bulatmain.account.application.port.gateway.UserGateway;
import com.bulatmain.account.application.port.request.RegisterUserRequest;
import com.bulatmain.account.application.usecase.RegisterUserUC;
import com.bulatmain.account.application.usecase.exception.UserAlreadyExistsException;
import com.bulatmain.account.domain.user.value.Id;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterUserUCImpl implements RegisterUserUC {
    private final UserGateway userGateway;
    private final UserFabric userFabric;
    private final EventPublisher eventPublisher;
    @Override
    public Id execute(RegisterUserRequest request)
            throws UserAlreadyExistsException, InvalidUserIdException, InvalidLoginException, InvalidPasswordException {
        if (userGateway.exists(request.getEmail())) {
            throw new UserAlreadyExistsException("Error: user with such email already exists!");
        }
        var user = userFabric.build(
                request.getEmail(),
                request.getLogin(),
                request.getPassword()
        );
        var userDTO = UserDTO.fromEntity(user);
        eventPublisher.publish(new UserRegisteredEvent(
                userDTO.getId()
        ));
        return () -> userGateway.save(userDTO);
    }
}
