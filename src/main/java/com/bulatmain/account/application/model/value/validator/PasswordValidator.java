package com.bulatmain.account.application.model.value.validator;

public class PasswordValidator implements StringValidator {
    private static final String pattern =
            "^(?=.*[a-zа-я])(?=.*[A-ZА-Я])(?=.*\\d)(?=.*[~!\\?@#$%^&*\\_\\-\\+\\(\\)\\{\\}\\[\\]<>\\/\\\\|\"'.:;]).{8,128}$";

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
