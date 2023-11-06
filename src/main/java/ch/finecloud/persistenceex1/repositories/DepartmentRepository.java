package ch.finecloud.persistenceex1.repositories;

import ch.finecloud.persistenceex1.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}