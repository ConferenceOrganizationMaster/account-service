package com.bulatmain.account.domain.user.value;


import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class UserId implements Id {
    private final Email email;

    public UserId(Email email) {
        this.email = email;
    }

    @Override
    public String get() {
        return email.get();
    }
}
