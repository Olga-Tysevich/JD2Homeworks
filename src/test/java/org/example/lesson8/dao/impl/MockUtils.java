package org.example.lesson8.dao.impl;

import org.example.lesson8.dto.DoorDTO;
import org.example.lesson8.dto.HouseDTO;
public class MockUtils {
    private MockUtils() {
    }

    public static DoorDTO buildDoor(double size, String type) {
        return DoorDTO.builder()
                .size(size)
                .type(type)
                .build();
    }

    public static HouseDTO buildHouse(double size, String color, int rooms) {
        return HouseDTO.builder()
                .size(size)
                .color(color)
                .roomCount(rooms)
                .build();
    }

}
