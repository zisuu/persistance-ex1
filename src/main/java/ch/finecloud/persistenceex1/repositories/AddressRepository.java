package ch.finecloud.persistenceex1.repositories;

import ch.finecloud.persistenceex1.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}