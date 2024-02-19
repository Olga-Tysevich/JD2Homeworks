package org.example.lesson10b3.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@ToString
@EqualsAndHashCode
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address implements Serializable {
    @Column
    private String city;
    @Column
    private String street;
}
