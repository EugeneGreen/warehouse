package ua.skillsup.practice.warehouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.skillsup.practice.warehouse.repositories.entities.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

}

