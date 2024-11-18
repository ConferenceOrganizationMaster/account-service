package com.bulatmain.account.application.usecase;

import com.bulatmain.account.application.model.value.exception.InvalidLoginException;
import com.bulatmain.account.application.model.value.exception.InvalidPasswordException;
import com.bulatmain.account.application.model.value.exception.InvalidUserIdException;
import com.bulatmain.account.application.port.request.RegisterUserRequest;
import com.bulatmain.account.application.usecase.exception.UserAlreadyExistsException;
import com.bulatmain.account.domain.user.value.Id;

public interface RegisterUserUC {

    public Id execute(RegisterUserRequest request) throws UserAlreadyExistsException, InvalidUserIdException, InvalidLoginException, InvalidPasswordException;
}
