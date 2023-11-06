package ch.finecloud.persistenceex1.repositories;

import ch.finecloud.persistenceex1.entities.Employee;
import ch.finecloud.persistenceex1.repositories.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class EmployeeRepositoryIT {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void createAndFind() {
        Employee employee = new Employee();
        employee.setName("Peter Muster");
        employee.setSalary(80000L);

        employeeRepository.saveAndFlush(employee);

        List<Employee> employees = employeeRepository.findAll();

        assertThat(employees).hasSize(1);
    }

}
