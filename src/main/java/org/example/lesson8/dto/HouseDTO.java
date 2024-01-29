package org.example.lesson8.dto;

import lombok.*;
import org.example.lesson8.annotations.Column;
import org.example.lesson8.annotations.PrimaryKey;
import org.example.lesson8.annotations.Table;


@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(databaseName = "lesson8", tableName = "houses")
public class HouseDTO {
    @PrimaryKey
    @Column(name = "id")
    private int id;
    @Column(name = "size")
    private double size;
    @Column(name = "color")
    private String color;
    @Column(name = "room_count")
    private int roomCount;
}
