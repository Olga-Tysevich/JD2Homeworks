package org.example.lesson9.dao.impl;

import org.example.lesson9.dao.PersonDAO;
import org.example.lesson9.dto.PersonDTO;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.function.Consumer;

import static org.example.lesson9.utils.Constants.*;

public class PersonDAOImpl extends DAOImpl<PersonDTO> implements PersonDAO {

    @Override
    public PersonDTO increaseAge(int id, int increment) {
        PersonDTO result = get(id);
        if (result != null) {
            EntityManager manager = getManager();
            Consumer<PersonDTO> increaseAge = (o) -> {
                Query query = manager.createNativeQuery(INCREASE_AGE, PersonDTO.class);
                query.setParameter(INCREMENT, increment)
                        .setParameter(ID, id);
                query.executeUpdate();
            };
            executeTransaction(increaseAge, result);
            manager.refresh(result);
        }
        return result;
    }

    @Override
    protected Class<PersonDTO> getClazz() {
        return PersonDTO.class;
    }
}
