package com.bulatmain.account.application.model.value.fabric;

import com.bulatmain.account.application.model.value.exception.InvalidPasswordException;
import com.bulatmain.account.application.model.value.validator.StringValidator;
import com.bulatmain.account.domain.user.value.Password;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
public class PasswordFabric implements StringObjectFabric<Password> {
    private final StringValidator validator;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Password build(String record) throws InvalidPasswordException {
        if (!validator.check(record)) {
            throw new InvalidPasswordException(String.format(
                    "Error: password record doesn't satisfy format: %s",
                    validator.format()
            ));
        }
        return new EncodedPassword(record, passwordEncoder);
    }

    private static class EncodedPassword implements Password {
        private final String hash;
        private final PasswordEncoder passwordEncoder;

        protected EncodedPassword(String record, PasswordEncoder passwordEncoder) {
            this.passwordEncoder = passwordEncoder;
            this.hash = this.passwordEncoder.encode(record);
        }

        @Override
        public String get() {
            return hash;
        }

        @Override
        public boolean sameAs(String raw) {
            return passwordEncoder.matches(raw, hash);
        }
    }
}
