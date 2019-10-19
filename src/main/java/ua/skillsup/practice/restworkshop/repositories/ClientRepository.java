package ua.skillsup.practice.restworkshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.skillsup.practice.restworkshop.repositories.entities.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

}

