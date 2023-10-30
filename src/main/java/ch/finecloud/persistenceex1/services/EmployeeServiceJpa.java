package ch.finecloud.persistenceex1.services;

import ch.finecloud.persistenceex1.entities.Employee;
import ch.finecloud.persistenceex1.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class EmployeeServiceJpa implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(int id, String name, long salary) {
        Employee savedEmployee = employeeRepository.save(new Employee(id, name, salary));
        return savedEmployee;
    }

    @Override
    public Optional<Employee> getEmployee(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee;
    }

    @Override
    public Employee updateEmployee(int id, String name, long salary) {
        return null;
    }

    @Override
    public void deleteEmployee(int id) {

    }
}
