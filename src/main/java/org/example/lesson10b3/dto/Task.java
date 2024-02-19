package org.example.lesson10b3.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks_b3")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column
    private Integer id;
    @Column
    private String name;
    @Column
    private String description;

}
