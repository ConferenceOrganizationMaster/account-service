package com.bulatmain.account.application.model.value.fabric;

import com.bulatmain.account.application.model.value.exception.*;
import com.bulatmain.account.domain.user.entity.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserFabric {
    private final UserIdFabric userIdFabric;
    private final LoginFabric loginFabric;
    private final PasswordFabric passwordFabric;
    private final NameFabric nameFabric;
    private final PhoneFabric phoneFabric;

    public User build(String sEmail, String sLogin, String sPassword)
            throws InvalidUserIdException, InvalidLoginException, InvalidPasswordException {
        var userId = userIdFabric.build(sEmail);
        var login = loginFabric.build(sLogin);
        var password = passwordFabric.build(sPassword);

        return new User(userId, login, password);
    }

    public void setDetail(User user, String fullName, String sPhone)
            throws InvalidPhoneException, InvalidNameException {
        setName(user, fullName);
        setPhone(user, sPhone);
    }

    public void setName(User user, String fullName)
            throws InvalidNameException {
        var name = (fullName == null ? null : nameFabric.build(fullName));
        user.setName(name);
    }

    public void setPhone(User user, String sPhone)
            throws InvalidPhoneException {
        var phone = (sPhone == null ? null : phoneFabric.build(sPhone));
        user.setPhone(phone);
    }

}
