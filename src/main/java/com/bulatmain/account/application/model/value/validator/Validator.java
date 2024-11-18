package com.bulatmain.account.application.model.value.validator;

public interface Validator<T> {
    boolean check(T obj);
}