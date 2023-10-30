package ch.finecloud.persistenceex1.repositories;

import ch.finecloud.persistenceex1.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
