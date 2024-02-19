package org.example.lesson9.dao.impl;

import org.example.lesson9.dao.PersonDAO;
import org.example.lesson9.dto.PersonDTO;

import javax.persistence.Query;

import java.util.function.Supplier;

import static org.example.lesson9.utils.Constants.*;

public class PersonDAOImpl extends DAOImpl<PersonDTO> implements PersonDAO {

    @Override
    public PersonDTO increaseAge(int id, int increment) {
        Supplier<PersonDTO> increaseAge = () -> {
            Query query = createNativeQuery(INCREASE_AGE, PersonDTO.class);
            query.setParameter(INCREMENT, increment)
                    .setParameter(ID, id);
            query.executeUpdate();
            PersonDTO result = get(id);
            if (result != null) {
                refreshObject(result);
            }
            return result;
        };
        return executeTransaction(increaseAge);
    }

    @Override
    protected Class<PersonDTO> getClazz() {
        return PersonDTO.class;
    }
}
