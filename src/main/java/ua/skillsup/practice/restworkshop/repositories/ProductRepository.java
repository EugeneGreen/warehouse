package ua.skillsup.practice.restworkshop.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.skillsup.practice.restworkshop.repositories.entities.ProductEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByDateUpdateBetween(LocalDateTime from, LocalDateTime to);

    List<ProductEntity> findByDateUpdateAfter(LocalDateTime from);

    List<ProductEntity> findByDateUpdateBefore(LocalDateTime to);

}
