package com.oskarro.codecity.repositories;

import com.oskarro.codecity.entities.Coder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * User repository for CRUD operations.
 */

@Repository
public interface CoderRepository extends JpaRepository<Coder, Long> {

    Coder findByUsername(String username);

}
