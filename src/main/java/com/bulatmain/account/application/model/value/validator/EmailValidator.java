package com.bulatmain.account.application.model.value.validator;

public class EmailValidator implements StringValidator {
    private final String pattern =
            "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    @Override
    public boolean check(String obj) {
        if (obj == null) {
            return false;
        }
        return obj.matches(pattern);
    }

    @Override
    public String format() {
        return pattern;
    }
}
