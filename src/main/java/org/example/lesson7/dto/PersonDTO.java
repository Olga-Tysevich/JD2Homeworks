package org.example.lesson7.dto;

import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class PersonDTO {
    private int id;
    private int age;
    private double salary;
    private String passport;
    private String address;
    private Date dateOfBirthday;
    private Timestamp dateTimeCreate;
    private Time timeToLunch;
    private String letter;
}
