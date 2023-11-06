package ch.finecloud.persistenceex1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="employee_seq")
    @SequenceGenerator(name = "employee_seq", sequenceName = "employee_seq", initialValue = 1000)
    private Integer id;
    private String name;
    private long salary;
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee boss;
    @OneToMany(mappedBy = "boss", cascade = CascadeType.REFRESH)
    private Set<Employee> directs = new HashSet<>();
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("type")
    private List<Phone> phones = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Department department;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;
    @ManyToMany(mappedBy = "employees", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Set<Project> projects = new HashSet<>();

    public void addDirect(Employee employee) {
        employee.setBoss(this);
        directs.add(employee);
    }

    public void removeDirect(Employee employee) {
        employee.setBoss(null);
        directs.remove(employee);
    }

    public void addPhone(Phone phone) {
        phone.setEmployee(this);
        phones.add(phone);
    }

    public void removePhone(Phone phone) {
        phone.setEmployee(null);
        phones.remove(phone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;

        return getId() != null ? getId().equals(employee.getId()) : employee.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
