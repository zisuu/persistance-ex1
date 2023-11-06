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
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="phone_seq")
    @SequenceGenerator(name = "phone_seq", sequenceName = "phone_seq", initialValue = 1000)
    private Integer id;
    private String number;
    @Enumerated(EnumType.STRING)
    private PhoneType type;
    @ManyToOne(optional = false)
    private Employee employee;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phone phone)) return false;

        return getNumber().equals(phone.getNumber());
    }

    @Override
    public int hashCode() {
        return getNumber().hashCode();
    }
}
