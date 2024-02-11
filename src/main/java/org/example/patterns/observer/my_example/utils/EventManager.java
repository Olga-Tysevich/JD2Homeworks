package org.example.patterns.observer.my_example.utils;

import org.example.patterns.observer.my_example.events.EventAction;
import org.example.patterns.observer.my_example.listeners.EventListener;

import java.util.*;

public class EventManager {
    Map<EventTypes, List<EventListener>> listeners = new HashMap<>();

    public EventManager(List<EventTypes> eventType) {
        eventType.forEach(e -> listeners.put(e, new ArrayList<>()));
    }

    public void signSubscriber(EventTypes eventType, EventListener listener) {
        listeners.get(eventType).add(listener);
    }

    public void unsubscribeSubscriber(EventTypes eventType, EventListener listener) {
        listeners.get(eventType).remove(listener);
    }

    public void notify(EventTypes eventType, EventAction action) {
        listeners.get(eventType).forEach(l -> l.update(eventType, action));
    }
}
