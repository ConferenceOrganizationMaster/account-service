package com.bulatmain.account.application.model.value.fabric;

import com.bulatmain.account.application.model.value.exception.InvalidEmailException;
import com.bulatmain.account.application.model.value.exception.InvalidUserIdException;
import com.bulatmain.account.domain.user.value.UserId;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserIdFabric implements StringObjectFabric<UserId> {
    private EmailFabric emailFabric;

    @Override
    public UserId build(String record) throws InvalidUserIdException {
        try {
            return new UserId(emailFabric.build(record));
        } catch (InvalidEmailException e) {
            throw new InvalidUserIdException(e.getMessage());
        }
    }
}
