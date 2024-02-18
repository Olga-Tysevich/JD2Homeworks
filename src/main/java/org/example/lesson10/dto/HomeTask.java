package org.example.lesson10.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "home_tasks_br_1")
@DiscriminatorValue("H")
public class HomeTask extends Task {
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "city", column = @Column(name = "home_task_city")),
        @AttributeOverride(name = "street", column = @Column(name = "home_task_street"))
    })
    private Address address;
}