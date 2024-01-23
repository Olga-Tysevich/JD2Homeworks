package org.example.patterns.observer.my_example.events;

import static org.example.patterns.observer.my_example.utils.Constants.LUNCH_MESSAGE;

public class Lunch implements EventAction {

    @Override
    public void printAction() {
        System.out.println(LUNCH_MESSAGE);
    }
}
