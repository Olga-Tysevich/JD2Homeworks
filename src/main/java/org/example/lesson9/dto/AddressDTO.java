package org.example.lesson9.dto;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@EqualsAndHashCode(exclude = "people")
@ToString(exclude = "people")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses_join_many_to_many")
public class AddressDTO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "street")
    private String street;
    @Column(name = "house")
    private int house;
    @ManyToMany(mappedBy = "addresses", fetch = FetchType.LAZY)
    private Set<PersonDTO> people = new HashSet<>();

    protected void setPeople(Set<PersonDTO> people) {
        this.people = people;
    }

    public void addPerson(PersonDTO person) {
        people.add(person);
    }

    public void deletePerson(PersonDTO person) {
        people.remove(person);
    }
}
