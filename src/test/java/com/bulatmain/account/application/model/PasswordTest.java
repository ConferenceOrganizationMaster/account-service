package com.bulatmain.account.application.model;

import com.bulatmain.account.domain.user.value.Password;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PasswordTest implements Password {
    public String record;

    @Override
    public String get() {
        return record;
    }

    @Override
    public boolean sameAs(String raw) {
        if (record == null) {
            return true;
        }
        return record.equals(raw);
    }
}
