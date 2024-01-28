package org.example.lesson8.dto;

import lombok.*;
import org.example.lesson8.annotations.Column;
import org.example.lesson8.annotations.GeneratedColumn;
import org.example.lesson8.annotations.PrimaryKey;
import org.example.lesson8.annotations.Table;

import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Date;


@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(databaseName = "people", tableName = "person")
public class PersonDTO {
    @PrimaryKey
    @Column(name = "id")
    private int id;
    @Column(name = "age")
    private int age;
    @Column(name = "salary")
    private double salary;
    @Column(name = "passport")
    private String passport;
    @Column(name = "address")
    private String address;
    @Column(name = "date_of_birthday")
    private Date dateOfBirthday;
    @GeneratedColumn()
    @Column(name = "date_time_create")
    private Timestamp dateTimeCreate;
    @Column(name = "time_to_lunch")
    private Time timeToLunch;
    @Column(name = "letter")
    private String letter;
}
