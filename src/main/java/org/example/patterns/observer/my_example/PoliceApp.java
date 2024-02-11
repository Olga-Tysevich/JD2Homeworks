package org.example.patterns.observer.my_example;

import org.example.patterns.observer.my_example.listeners.OfficeWorker;
import org.example.patterns.observer.my_example.listeners.PolicePost;
import org.example.patterns.observer.my_example.subjects.PoliceDepartment;
import org.example.patterns.observer.my_example.utils.EventTypes;

import java.util.List;

import static org.example.patterns.observer.my_example.utils.EventTypes.LUNCH;
import static org.example.patterns.observer.my_example.utils.EventTypes.VIOLATION;

public class PoliceApp {
    public static void main(String[] args) {
        PoliceDepartment department = new PoliceDepartment(1);
        List<EventTypes> eventTypes = List.of(VIOLATION);
        department.addDepartment(eventTypes, new PolicePost(1));
        department.addDepartment(eventTypes, new PolicePost(2));
        department.addDepartment(eventTypes, new PolicePost(3));


        List<EventTypes> workersEventTypes = List.of(LUNCH);
        List<EventTypes> harmfulWorkerEventTypes = List.of(VIOLATION, LUNCH);
        department.addDepartment(workersEventTypes, new OfficeWorker(4));
        department.addDepartment(workersEventTypes, new OfficeWorker(5));
        department.addDepartment(harmfulWorkerEventTypes, new OfficeWorker(6));


        department.reportTransportViolation("some route");
        System.out.println("\n");
        department.informAboutLunchTime();
    }
}
