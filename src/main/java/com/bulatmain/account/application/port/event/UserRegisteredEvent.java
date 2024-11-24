package com.bulatmain.account.application.port.event;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserRegisteredEvent implements Event {

    private String userId;
}
