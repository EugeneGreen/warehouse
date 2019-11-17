package ua.skillsup.practice.warehouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.skillsup.practice.warehouse.repositories.entities.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

}

