package com.bulatmain.account.domain.user.value.detail;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDetail {
    private Name name;
    private Phone phone;
}
