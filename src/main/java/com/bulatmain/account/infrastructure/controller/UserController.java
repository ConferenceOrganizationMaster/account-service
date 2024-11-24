package com.bulatmain.account.infrastructure.controller;

import com.bulatmain.account.application.model.value.exception.InvalidLoginException;
import com.bulatmain.account.application.model.value.exception.InvalidPasswordException;
import com.bulatmain.account.application.model.value.exception.InvalidUserIdException;
import com.bulatmain.account.application.port.request.RegisterUserRequest;
import com.bulatmain.account.application.usecase.RegisterUserUC;
import com.bulatmain.account.application.usecase.exception.UserAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/user")
@AllArgsConstructor
public class UserController {
    @Autowired
    private final RegisterUserUC registerUserUc;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterUserRequest request) {
        try {
            var id = registerUserUc.execute(request);
            return new ResponseEntity<>(id.get(), HttpStatus.OK);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(
                    String.format(
                            "Error: User with email %s already exists",
                            request.getEmail()
                    ),
                    HttpStatus.BAD_REQUEST
            );
        } catch (InvalidUserIdException | InvalidLoginException | InvalidPasswordException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
