package com.bulatmain.account.application.model.dto;

import com.bulatmain.account.domain.common.value.StringObject;
import com.bulatmain.account.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private final String id;
    private final String login;
    private final String password;
    private final String fullName;
    private final String phone;

    public static UserDTO fromEntity(User user) {
        return UserDTO.builder()
                .id(user.getId().get())
                .login(user.getLogin().get())
                .password(user.getPassword().get())
                .fullName(recordOrNull(user.getName()))
                .password(recordOrNull(user.getPhone()))
                .build();
    }

    private static String recordOrNull(StringObject obj) {
        if (obj == null) {
            return null;
        }
        return obj.get();
    }

}
