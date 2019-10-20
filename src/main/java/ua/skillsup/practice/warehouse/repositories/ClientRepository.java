package ua.skillsup.practice.warehouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.skillsup.practice.warehouse.repositories.entities.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

}

