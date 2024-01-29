package org.example.lesson8.dao.impl;

import org.example.lesson8.dao.DAO;
import org.example.lesson8.dto.DoorDTO;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.SQLException;
import java.util.stream.Stream;

import static org.example.lesson8.dao.impl.MockConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class DAOImplTest<T> {
    private final DAO<T> DAO = new DAOImpl<>();



    @ParameterizedTest()
    @MethodSource("cases")
    public void saveTest(T expected, Class<T> clazz) {
        try {
            if (expected != null) {
                T actual = DAO.save(expected, clazz);
                assertNotNull(actual);
            } else {
                assertThrows(IllegalArgumentException.class, () -> DAO.save(expected, clazz));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @ParameterizedTest()
    @MethodSource("cases")
    public void updateTest(T dto, Class<T> clazz) {
        try {
            int expectedRows = 1;

            if (dto != null) {
                T dto2 = DAO.save(dto, clazz);

                int actualRows = DAO.update(dto2);

                assertEquals(expectedRows, actualRows);
            } else {
                assertThrows(IllegalArgumentException.class, () -> DAO.save(dto, clazz));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @ParameterizedTest()
    @MethodSource("cases")
    public void deleteTest(T dto, Class<T> clazz) {
        try {
            if (dto != null) {
                DAO.save(dto, clazz);
                int deletedRow = DAO.delete(1, clazz);
                int expected = 1;

                assertEquals(expected, deletedRow);
            } else {
                assertThrows(IllegalArgumentException.class, () -> DAO.save(dto, clazz));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    static Stream<Arguments> cases() {
        return Stream.of(

                Arguments.of(MockUtils.buildDoor(DOORS_SIZE.get(0), DOORS_TYPE.get(0)), DoorDTO.class),

                Arguments.of(MockUtils.buildDoor(DOORS_SIZE.get(1), DOORS_TYPE.get(1)), DoorDTO.class),

                Arguments.of(MockUtils.buildDoor(DOORS_SIZE.get(2), DOORS_TYPE.get(2)), DoorDTO.class),

                Arguments.of(MockUtils.buildDoor(DOORS_SIZE.get(3), DOORS_TYPE.get(3)), DoorDTO.class),

                Arguments.of(MockUtils.buildDoor(DOORS_SIZE.get(4), DOORS_TYPE.get(4)), DoorDTO.class),

                Arguments.of(null, DoorDTO.class)
        );
    }

}