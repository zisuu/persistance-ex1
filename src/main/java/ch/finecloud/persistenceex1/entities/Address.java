package ch.finecloud.persistenceex1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="address_seq")
    @SequenceGenerator(name = "address_seq", sequenceName = "address_seq", initialValue = 1000)
    private Integer id;
    private String street;
    private String city;
    private String state;
    private String zip;
}
