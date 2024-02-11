package org.example.lesson9.utils_src;

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
}
