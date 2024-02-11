package org.example.patterns.observer.my_example.listeners;

import org.example.patterns.observer.my_example.events.EventAction;
import org.example.patterns.observer.my_example.utils.EventTypes;

import static org.example.patterns.observer.my_example.utils.Constants.*;

public class OfficeWorker implements EventListener {
    private int id;

    public OfficeWorker(int id) {
        this.id = id;
    }

    @Override
    public void update(EventTypes eventType, EventAction action) {
        if (eventType == EventTypes.LUNCH) {
            action.printAction();
            System.out.println(NAME_WORKER + id + LUNCH_MESSAGE_WORKER);
        } else if (eventType == EventTypes.VIOLATION) {
            System.out.printf(LISTENING_MESSAGE_WORKER, NAME_WORKER + id);
        }
    }
}
