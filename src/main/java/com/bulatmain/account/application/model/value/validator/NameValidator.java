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
        return obj.matches(pattern);
    }
}
