package com.bulatmain.account.application.model.value.fabric;

import com.bulatmain.account.application.model.value.exception.InvalidRecordException;

public interface StringObjectFabric<T> {
    T build(String record) throws InvalidRecordException;
}
