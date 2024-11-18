package com.bulatmain.account.application.model.value.fabric;

import com.bulatmain.account.application.model.value.exception.InvalidLoginException;
import com.bulatmain.account.application.model.value.validator.StringValidator;
import com.bulatmain.account.domain.user.value.Login;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginFabric implements StringObjectFabric<Login>{
    private final StringValidator validator;
    @Override
    public Login build(String record) throws InvalidLoginException {
        if (!validator.check(record)) {
            throw new InvalidLoginException(String.format(
                    "Error: login record doesn't satisfy format: %s",
                    validator.format()
            ));
        }
        return new LoginImpl(record);
    }

    private static class LoginImpl implements Login {
        private final String record;

        protected LoginImpl(String record) {
            this.record = record;
        }

        @Override
        public String get() {
            return record;
        }
    }
}


