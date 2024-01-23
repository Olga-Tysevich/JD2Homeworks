package org.example.patterns.observer.my_example.subjects;

import org.example.patterns.observer.my_example.events.Lunch;
import org.example.patterns.observer.my_example.events.Violation;
import org.example.patterns.observer.my_example.listeners.EventListener;
import org.example.patterns.observer.my_example.utils.EventManager;
import org.example.patterns.observer.my_example.utils.EventTypes;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.patterns.observer.my_example.utils.Constants.NAME_DEPARTMENT;
import static org.example.patterns.observer.my_example.utils.Constants.VIOLATION_MESSAGE_DEPARTMENT;
import static org.example.patterns.observer.my_example.utils.EventTypes.LUNCH;
import static org.example.patterns.observer.my_example.utils.EventTypes.VIOLATION;

public class PoliceDepartment {
    private int id;
    private EventManager manager;

    public PoliceDepartment(int id) {
        this.id = id;
        this.manager = new EventManager(Arrays.stream(EventTypes.values()).collect(Collectors.toList()));
    }

    public void reportTransportViolation(String address) {
        System.out.println(NAME_DEPARTMENT + id + VIOLATION_MESSAGE_DEPARTMENT);
        manager.notify(VIOLATION, new Violation(address));
    }

    public void informAboutLunchTime() {
        manager.notify(LUNCH, new Lunch());
    }

    public void addDepartment(List<EventTypes> events, EventListener listener) {
        events.forEach(e -> manager.signSubscriber(e, listener));
    }
}
