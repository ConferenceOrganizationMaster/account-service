package com.bulatmain.account.domain.user.entity;

import com.bulatmain.account.domain.user.value.Id;
import com.bulatmain.account.domain.user.value.Login;
import com.bulatmain.account.domain.user.value.Password;
import com.bulatmain.account.domain.user.value.UserId;
import com.bulatmain.account.domain.user.value.detail.Name;
import com.bulatmain.account.domain.user.value.detail.Phone;
import com.bulatmain.account.domain.user.value.detail.UserDetail;
import lombok.Getter;

@Getter
public class User {
    private final Id id;
    private Login login;
    private Password password;
    private UserDetail detail =
            new UserDetail(null, null);

    public User(UserId id, Login login, Password password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public Id getId() {
        return id;
    }

    public void setDetail(UserDetail detail) {
        this.detail = detail;
    }

    public void setName(Name name) {
        detail.setName(name);
    }

    public Name getName() {
        return detail.getName();
    }

    public void setPhone(Phone phone) {
        detail.setPhone(phone);
    }

    public Phone getPhone() {
        return detail.getPhone();
    }

}
