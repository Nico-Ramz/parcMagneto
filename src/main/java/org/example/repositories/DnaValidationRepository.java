package org.example.repositories;

import org.example.entities.DnaValidation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DnaValidationRepository extends JpaRepository <DnaValidation, String>{
    Optional<DnaValidation> findByDna(String[] dna);

    long countByIsMutant(boolean isMutant);

}
