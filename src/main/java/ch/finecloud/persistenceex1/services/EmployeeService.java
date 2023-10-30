package ch.finecloud.persistenceex1.services;

import ch.finecloud.persistenceex1.entities.Employee;

import java.util.Optional;

public interface EmployeeService {
    Employee createEmployee(int id, String name, long salary);
    Optional<Employee> getEmployee(int id);
    Employee updateEmployee(int id, String name, long salary);
    void deleteEmployee(int id);
}
