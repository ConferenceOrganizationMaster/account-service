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
import com.bulatmain.account.domain.user.entity.User;
import com.bulatmain.account.domain.user.value.Id;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class RegisterUserUCImpl implements RegisterUserUC {
    private final UserGateway userGateway;
    private final UserFabric userFabric;
    private final EventPublisher eventPublisher;

    /**
     * Runs register User use-case
     *
     * @param request register User request
     * @return registered User id
     */
    @Override
    public Id execute(RegisterUserRequest request)
            throws UserAlreadyExistsException, InvalidUserIdException, InvalidLoginException, InvalidPasswordException {
        checkIfUserExists(request);

        var user = buildDomainEntity(request);

        var userDTO = UserDTO.fromEntity(user);

        publishEvent(userDTO.getId());

        return saveAccount(userDTO);
    }

    /**
     * Check if User with given email exists
     * <p>
     * At current business model user email is his unique id
     *
     * @param request Register User request
     */
    public void checkIfUserExists(RegisterUserRequest request) throws UserAlreadyExistsException {
        if (userGateway.exists(request.getEmail())) {
            throw new UserAlreadyExistsException("Error: user with such email already exists!");
        }
    }

    /**
     * Creates domain User entity to verify and prepare request data
     *
     * @param request register user request
     * @return domain User entity
     */
    public User buildDomainEntity(RegisterUserRequest request)
            throws InvalidUserIdException, InvalidLoginException, InvalidPasswordException {
        return userFabric.build(
                request.getEmail(),
                request.getLogin(),
                request.getPassword()
        );
    }

    /**
     * Publishes UserRegisteredEvent event
     *
     * @param userId - registered user id
     */
    public void publishEvent(String userId) {
        var event = new UserRegisteredEvent(userId);
        eventPublisher.publish(event);
        log.debug("Event {} published", event.getRecord());
    }

    /**
     * Persists a User account
     *
     * @param userDTO User entity DTO
     * @return registered User id
     */
    public Id saveAccount(UserDTO userDTO) {
        Id id = () -> userGateway.save(userDTO);
        log.info("User with id {} registered", userDTO.getId());
        return id;
    }
}
