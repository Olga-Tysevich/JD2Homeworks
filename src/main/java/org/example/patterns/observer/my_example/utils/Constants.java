package org.example.patterns.observer.my_example.utils;

public class Constants {
    //events
    //Lunch
    public static final String LUNCH_MESSAGE = "Lunch time!";
    //Violation
    public static final String VIOLATION_MESSAGE = "A violation has occurred! The criminal follows the route %s! Keep your eyes open!\n";

    //listeners
    //OfficeWorker
    public static final String NAME_WORKER = "Office worker №";
    public static final String LUNCH_MESSAGE_WORKER = " went to lunch";
    public static final String LISTENING_MESSAGE_WORKER = "%s eavesdrops on conversations on a walkie-talkie.";
    //PolicePost
    public static final String NAME_POST = "Police post №";
    public static final String VIOLATION_MESSAGE_POST = " carefully looks out for intruders in his area";

    //PoliceDepartment
    public static final String NAME_DEPARTMENT = "Police department №";
    public static final String VIOLATION_MESSAGE_DEPARTMENT = " notified posts";



    private Constants() {
    }
}
