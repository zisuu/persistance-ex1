package ch.finecloud.persistenceex1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="department_seq")
    @SequenceGenerator(name = "department_seq", sequenceName = "department_seq", initialValue = 1000)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "department")
    private Set<Employee> employees = new HashSet<>();
}
