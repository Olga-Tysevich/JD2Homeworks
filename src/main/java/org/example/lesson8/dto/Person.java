package org.example.lesson8.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "person")
//@NamedNativeQuery(name = "select_by_age",
//        query = "select * from person where age > :age order by date_time_create",
//        resultClass = Person.class)
@FilterDef(
        name = "overAge",
        parameters = @ParamDef(name = "maxAge", type="integer")
)
@Filter(
        name = "overAge",
        condition = "age > :maxAge"
)
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirthday;
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @Column(name = "date_time_create")
    private Timestamp dateTimeCreate;
    @Column(name = "time_to_lunch")
    private Time timeToLunch;
}
