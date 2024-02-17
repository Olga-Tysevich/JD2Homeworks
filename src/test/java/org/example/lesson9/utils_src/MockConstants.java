package org.example.lesson9.utils_src;

public class MockConstants {
    public static final String PERSONS_JSON = "src\\test\\resources\\persons.json";
    public static final String ADDRESSES_JSON = "src\\test\\resources\\addresses.json";
    public static final String TEST_UPDATE = "TEST";
    public static final String DELETE_ALL_ADDRESSES = "delete from addresses_join_many_to_many";
    public static final String DELETE_ALL_PEOPLE = "delete from people_join_many_to_many";
    public static final int PERSON_AGE = 21;
    public static final String PERSON_NAME = "New name";
    public static final String PERSON_SURNAME = "New surname";
    public static final String ADDRESS_STREET = "some street";
    public static final int ADDRESS_HOUSE = 1;
    public static final String DELETE_ALL_PEOPLE_ADDRESSES = "delete from people_addresses_join_many_to_many";

    private MockConstants() {
    }
}
