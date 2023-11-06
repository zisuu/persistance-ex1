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
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="project_seq")
    @SequenceGenerator(name = "project_seq", sequenceName = "project_seq", initialValue = 1000)
    private Integer id;
    private String name;
    @ManyToMany
    private Set<Employee> employees = new HashSet<>();
}
