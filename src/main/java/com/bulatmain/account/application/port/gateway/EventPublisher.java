package com.bulatmain.account.application.port.gateway;

import com.bulatmain.account.application.port.event.Event;

public interface EventPublisher {
    void publish(Event event);
}
