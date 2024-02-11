package org.example.patterns.observer.my_example.listeners;

import org.example.patterns.observer.my_example.utils.EventTypes;
import org.example.patterns.observer.my_example.events.EventAction;

public interface EventListener {
    void update(EventTypes eventType, EventAction action);
}
