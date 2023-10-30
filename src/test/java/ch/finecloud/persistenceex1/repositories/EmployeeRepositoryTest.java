package ch.finecloud.persistenceex1.repositories;

import ch.finecloud.persistenceex1.entities.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Test
    void testAddEmployee() {
        Employee employee = new Employee(1, "testEmployee", 1000L);
        employeeRepository.saveAndFlush(employee); // Use saveAndFlush for a single entity
        Employee savedEmployee = employeeRepository.findById(1).get();
        assertNotNull(savedEmployee);
        assertEquals(employee.getName(), savedEmployee.getName());
    }

    @Test
    void testUpdateEmployee() {
        Employee employee = new Employee(1, "testEmployee", 1000L);
        employeeRepository.saveAndFlush(employee);
        Employee savedEmployee = employeeRepository.findById(1).get();
        assertNotNull(savedEmployee);
        assertEquals(employee.getName(), savedEmployee.getName());
        savedEmployee.setName("newName");
        employeeRepository.saveAndFlush(savedEmployee);
        Employee updatedEmployee = employeeRepository.findById(1).get();
        assertEquals("newName", updatedEmployee.getName());
    }

    @Test
    void findAll() {
        Employee employee1 = new Employee(1, "testEmployee1", 1000L);
        Employee employee2 = new Employee(2, "testEmployee2", 2000L);
        employeeRepository.saveAndFlush(employee1);
        employeeRepository.saveAndFlush(employee2);
        assertEquals(2, employeeRepository.findAll().size());
    }

}