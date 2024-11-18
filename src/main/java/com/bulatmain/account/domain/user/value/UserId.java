package com.bulatmain.account.domain.user.value;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@AllArgsConstructor
public class UserId implements Id {
    private final Email email;
    @Override
    public String get() {
        return email.get();
    }
}
