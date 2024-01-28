package org.example.lesson8.dto;

import lombok.*;
import org.example.lesson8.annotations.Column;
import org.example.lesson8.annotations.PrimaryKey;
import org.example.lesson8.annotations.Table;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Table(databaseName = "doors_data", tableName = "doors")
public class DoorDTO {
    @PrimaryKey
    @Column(name = "id")
    private int id;
    @Column(name = "size")
    private double size;
    @Column(name = "type")
    private String type;
}
