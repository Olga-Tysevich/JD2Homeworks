package org.example.lesson10.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "work_tasks_br_1")
@DiscriminatorValue("W")
public class WorkTask extends Task {
    @Column
    private double cost;

    @Builder(builderMethodName = "WorkTaskBuilder")
    public WorkTask(Integer id, String name, String description, double cost) {
        super(id, name, description);
        this.cost = cost;
    }
}
