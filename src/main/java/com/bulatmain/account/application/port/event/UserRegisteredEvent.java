package com.bulatmain.account.application.port.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegisteredEvent implements Event {

    private String userId;

    @Override
    public String getRecord() {
        return String.format(
                "UserRegisteredEvent { userId: %s }",
                userId
        );
    }
}
