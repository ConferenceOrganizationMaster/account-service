package com.bulatmain.account.application.usecase;

import com.bulatmain.account.application.model.PasswordTest;
import com.bulatmain.account.application.model.value.exception.InvalidLoginException;
import com.bulatmain.account.application.model.value.fabric.UserFabric;
import com.bulatmain.account.application.port.event.Event;
import com.bulatmain.account.application.port.gateway.EventPublisher;
import com.bulatmain.account.application.port.gateway.UserGateway;
import com.bulatmain.account.application.port.request.RegisterUserRequest;
import com.bulatmain.account.application.usecase.exception.UserAlreadyExistsException;
import com.bulatmain.account.application.usecase.impl.RegisterUserUCImpl;
import com.bulatmain.account.domain.user.entity.User;
import com.bulatmain.account.domain.user.value.Id;
import com.bulatmain.account.domain.user.value.Login;
import com.bulatmain.account.domain.user.value.Password;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
public class RegisterUserUCTest {
    @Mock
    private UserGateway userGateway;
    @Mock
    private UserFabric userFabric;
    @Mock
    private EventPublisher eventPublisher;

    @InjectMocks
    private RegisterUserUCImpl registerUserUC;

    /**
     * In this use case tests we don't care
     * whether event is published or not
     * so I'll just mock it
     */
    @BeforeEach
    public void mockEventPublisher() {
        Mockito.lenient()
                .doNothing()
                .when(eventPublisher)
                .publish(Mockito.any(Event.class));
    }

    public User mockUser(String userId, String login, String password) {
        var user = Mockito.mock(User.class);
        Id _id = () -> userId;
        Mockito.lenient()
                .doReturn(_id)
                .when(user)
                .getId();

        Login _login = () -> login;
        Mockito.lenient()
                .doReturn(_login)
                .when(user)
                .getLogin();

        Password _password = new PasswordTest(password);
        Mockito.lenient()
                .doReturn(_password)
                .when(user)
                .getPassword();

        return user;
    }

    public void mockUserFabricValid(String userId, String login, String password) {
        var user = mockUser(userId, login, password);
        try {
            Mockito.lenient()
                    .doReturn(user)
                    .when(userFabric)
                    .build(
                            Mockito.anyString(),
                            Mockito.anyString(),
                            Mockito.anyString()
                    );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends Exception>  void mockUserFabricThrows(T exception) {
        try {
            Mockito.doThrow(exception)
                    .when(userFabric)
                    .build(
                            Mockito.anyString(),
                            Mockito.anyString(),
                            Mockito.anyString()
                    );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void mockUserGatewayExists(boolean exists) {
        Mockito.doReturn(exists)
                .when(userGateway)
                .exists(Mockito.anyString());
    }

    @Test
    public void RegisterUniqueUserWithValidData() {
        var request = userOne();

        mockUserFabricValid(
                request.getEmail(),
                request.getLogin(),
                request.getPassword()
        );
        mockUserGatewayExists(false);

        Assertions.assertDoesNotThrow(
                () -> registerUserUC.execute(request)
        );
    }

    @Test
    public void RegisterExistingUserWithValidData() {
        var request = userOne();

        mockUserFabricValid(
                request.getEmail(),
                request.getLogin(),
                request.getPassword()
        );
        mockUserGatewayExists(true);

        Assertions.assertThrows(
                UserAlreadyExistsException.class,
                () -> registerUserUC.execute(request)
        );
    }

    @Test
    public void RegisterUniqueUserInvalidLogin() {
        var request = userOne();

        this.<InvalidLoginException>mockUserFabricThrows(new InvalidLoginException());
        mockUserGatewayExists(false);

        Assertions.assertThrows(
                InvalidLoginException.class,
                () -> registerUserUC.execute(request)
        );
    }

    public RegisterUserRequest userOne() {
        return RegisterUserRequest.builder()
                .email("user@one.com")
                .login("user-one")
                .password("password")
                .build();
    }
}
