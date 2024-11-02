package org.example.controllers;


import org.example.dto.dnaSequenceDTO;
import org.example.services.DnaValidationServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mutant")
public class DnaValidationController {

    private final DnaValidationServices dnaValidationServices;

    public DnaValidationController(DnaValidationServices dnaValidationServices) {
        this.dnaValidationServices = dnaValidationServices;
    }

    @PostMapping
    public ResponseEntity<String> checkMutant(@RequestBody dnaSequenceDTO dnaSequence) {
        String[] dna = dnaSequence.getDna();  // Extraemos el array de ADN del DTO
        boolean isMutant = dnaValidationServices.isMutant(dna);
        if (isMutant) {
            return ResponseEntity.ok("Mutant DNA detected!");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not a mutant.");
        }
    }
}
