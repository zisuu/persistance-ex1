package ch.finecloud.persistenceex1.repositories;

import ch.finecloud.persistenceex1.TestPersistenceEx1Application;
import ch.finecloud.persistenceex1.entities.Employee;
import ch.finecloud.persistenceex1.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Import(TestPersistenceEx1Application.class)
@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void save() {
        var employee = new Employee();
        employee.setName("Peter Muster");
        employee.setSalary(80000L);
        employee = employeeRepository.saveAndFlush(employee);

        assertNotNull(employee);
    }

    @Test
    void findAll() {
        var employee = new Employee();
        employee.setName("Jane Doe");
        employee.setSalary(90000L);
        employeeRepository.save(employee);

        var employees = employeeRepository.findAll();

        assertThat(employees).hasSize(1);
    }
}