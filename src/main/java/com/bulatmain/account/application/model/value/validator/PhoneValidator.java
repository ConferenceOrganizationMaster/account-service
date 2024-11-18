package com.bulatmain.account.application.model.value.validator;

public class PhoneValidator implements StringValidator{
    private final String pattern =
            "\\d{11}";
    @Override
    public String format() {
        return pattern;
    }

    @Override
    public boolean check(String obj) {
        return obj.matches(pattern);
    }
}
