package com.bulatmain.account.application.model.value.fabric;

import com.bulatmain.account.application.model.value.exception.InvalidNameException;
import com.bulatmain.account.application.model.value.exception.InvalidPhoneException;
import com.bulatmain.account.domain.user.value.detail.Name;
import com.bulatmain.account.domain.user.value.detail.Phone;
import com.bulatmain.account.domain.user.value.detail.UserDetail;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDetailFabric {
    private final NameFabric nameFabric;
    private final PhoneFabric phoneFabric;

    public UserDetail build(String fullName, String sPhone)
            throws InvalidNameException, InvalidPhoneException {
        Name name = nameOfNullable(fullName);
        Phone phone = phoneOfNullable(sPhone);

        return new UserDetail(name, phone);
    }

    private Name nameOfNullable(String fullName) throws InvalidNameException {
        return (fullName == null ? null : nameFabric.build(fullName));
    }

    private Phone phoneOfNullable(String sPhone) throws InvalidPhoneException {
        return (sPhone == null ? null : phoneFabric.build(sPhone));
    }
}
