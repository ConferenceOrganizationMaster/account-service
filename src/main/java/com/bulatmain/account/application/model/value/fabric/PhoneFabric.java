package com.bulatmain.account.application.model.value.fabric;

import com.bulatmain.account.application.model.value.exception.InvalidPhoneException;
import com.bulatmain.account.application.model.value.validator.StringValidator;
import com.bulatmain.account.domain.user.value.detail.Phone;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PhoneFabric implements StringObjectFabric<Phone> {
    private final StringValidator validator;
    @Override
    public Phone build(String record) throws InvalidPhoneException {
        if (!validator.check(record)) {
            throw new InvalidPhoneException(String.format(
                    "Error: phone record doesn't satisfy format: %s",
                    validator.format()
            ));
        }
        return new PhoneImpl(record);
    }

    private static class PhoneImpl implements Phone {
        private final String record;

        protected PhoneImpl(String record) {
            this.record = record;
        }

        @Override
        public String get() {
            return record;
        }
    }


}
