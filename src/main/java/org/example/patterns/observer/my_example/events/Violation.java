package org.example.patterns.observer.my_example.events;


import static org.example.patterns.observer.my_example.utils.Constants.VIOLATION_MESSAGE;

public class Violation implements EventAction {
    private  String address;

    public Violation(String address) {
        this.address = address;
    }

    public void printAction() {
        System.out.printf(VIOLATION_MESSAGE, address);
    }
}
