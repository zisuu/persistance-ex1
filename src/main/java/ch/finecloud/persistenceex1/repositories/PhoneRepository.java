package ch.finecloud.persistenceex1.repositories;

import ch.finecloud.persistenceex1.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Integer> {
}