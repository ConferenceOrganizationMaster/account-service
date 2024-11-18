package com.bulatmain.account.application.model.value.validator;

public class LoginValidator implements StringValidator {
    private final String pattern =
            "^[A-Za-z0-9]+([A-Za-z0-9]*|[._-]?[A-Za-z0-9]+)*$";
    @Override
    public String format() {
        return pattern;
    }

    @Override
    public boolean check(String obj) {
        return obj.matches(pattern);
    }
}
