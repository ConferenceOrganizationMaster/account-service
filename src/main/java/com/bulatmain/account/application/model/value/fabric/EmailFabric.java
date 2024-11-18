package com.bulatmain.account.application.model.value.fabric;

import com.bulatmain.account.application.model.value.exception.InvalidEmailException;
import com.bulatmain.account.application.model.value.validator.StringValidator;
import com.bulatmain.account.domain.user.value.Email;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmailFabric implements StringObjectFabric<Email> {
    private final StringValidator validator;

    @Override
    public Email build(String record) throws InvalidEmailException {
        if (!validator.check(record)) {
            throw new InvalidEmailException(String.format(
                    "Error: email record doesn't satisfy format: %s",
                    validator.format()
            ));
        }
        return new EmailImpl(record);
    }

    private static class EmailImpl implements Email {
        private final String record;

        protected EmailImpl(String record) {
            this.record = record;
        }

        @Override
        public String get() {
            return record;
        }

    }
}




