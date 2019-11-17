package ua.skillsup.practice.warehouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.skillsup.practice.warehouse.repositories.entities.ContactTypeEntity;

public interface ContactTypeRepository extends JpaRepository<ContactTypeEntity, Long> {

}

