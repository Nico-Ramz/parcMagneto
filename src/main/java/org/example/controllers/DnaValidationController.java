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

/*
import org.example.dto.dnaSequenceDTO;
import org.example.entities.DnaValidation;
import org.example.repositories.DnaValidationRepository;
import org.example.services.DnaValidationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")//Logramos brindar o permitir el acceso o no a nuestra Api a partir de disntos origines
@RequestMapping(path= "api/v1/DnaValidations") //Es nuestra ruta de acceso
public class DnaValidationController {

    @Autowired
    private DnaValidationServices dnaValidationServices;


    @PostMapping("/mutant")
    public ResponseEntity<String> checkMutant(@RequestBody String[] dna) {
        boolean isMutant = dnaValidationServices.isMutant(dna);
        if (isMutant) {
            return ResponseEntity.ok("Mutant DNA detected!");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not a mutant.");
        }
    }


}
*/