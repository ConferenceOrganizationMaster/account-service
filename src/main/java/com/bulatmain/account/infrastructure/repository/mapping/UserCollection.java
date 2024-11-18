package com.bulatmain.account.infrastructure.repository.mapping;

import com.bulatmain.account.application.model.dto.UserDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("user")
public class UserCollection {
    @Id
    private final String id;
    private final String login;
    private final String password;
    private final String fullName;
    private final String phone;

    public static UserCollection fromDTO(UserDTO userDTO) {
        return UserCollection.builder()
                .id(userDTO.getId())
                .login(userDTO.getLogin())
                .password(userDTO.getPassword())
                .fullName(userDTO.getFullName())
                .phone(userDTO.getPhone())
                .build();
    }

}
