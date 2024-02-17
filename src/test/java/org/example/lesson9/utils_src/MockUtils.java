package org.example.lesson9.utils_src;

import org.example.lesson9.dto.AddressDTO;
import org.example.lesson9.dto.PersonDTO;

import static org.example.lesson9.utils_src.MockConstants.*;

public class MockUtils {

    public static PersonDTO buildPerson() {
        return PersonDTO.builder()
                .name(PERSON_NAME)
                .surname(PERSON_SURNAME)
                .age(PERSON_AGE)
                .build();
    }

    public static AddressDTO buildAddress() {
        return AddressDTO.builder()
                .street(ADDRESS_STREET)
                .house(ADDRESS_HOUSE)
                .build();
    }
}
