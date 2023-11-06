package ch.finecloud.persistenceex1.repositories;

import ch.finecloud.persistenceex1.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}