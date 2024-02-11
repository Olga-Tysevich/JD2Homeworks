package org.example.patterns.observer.my_example.listeners;

import org.example.patterns.observer.my_example.events.EventAction;
import org.example.patterns.observer.my_example.utils.EventTypes;

import static org.example.patterns.observer.my_example.utils.Constants.*;
import static org.example.patterns.observer.my_example.utils.EventTypes.VIOLATION;

public class PolicePost implements EventListener{
    private final int id;

    public PolicePost(int id) {
        this.id = id;
    }

    @Override
    public void update(EventTypes eventType, EventAction action) {
       if (eventType == VIOLATION) {
           action.printAction();
           System.out.println(NAME_POST + id + VIOLATION_MESSAGE_POST);
       }
    }
}
