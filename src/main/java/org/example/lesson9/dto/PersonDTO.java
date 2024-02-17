package org.example.lesson9.dto;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "people_join_many_to_many")
public class PersonDTO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "age")
    private int age;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "people_addresses_join_many_to_many",
            joinColumns = {@JoinColumn(name = "person_id")},
            inverseJoinColumns = {@JoinColumn(name = "address_id")})
    private Set<AddressDTO> addresses = new HashSet<>();

    protected void setAddresses(Set<AddressDTO> addresses) {
        this.addresses = addresses;
    }

    public void addAddress(AddressDTO addressDTO) {
        addresses.add(addressDTO);
    }

    public void deleteAddress(AddressDTO addressDTO) {
        addresses.remove(addressDTO);
    }

}
