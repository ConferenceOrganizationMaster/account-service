package com.bulatmain.account.application.model.value.validator;

public class NameValidator implements StringValidator {
    private final String pattern =
            " *\\w* *\\w* *";

    @Override
    public String format() {
        return pattern;
    }

    @Override
    public boolean check(String obj) {
        if (obj == null) {
            return false;
        }
        return obj.matches(pattern);
    }
}
