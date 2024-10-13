package org.example.controllers;


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

/*
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).
                    body(dnaValidationServices.findAll());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable String id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(dnaValidationServices.findById(id));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody DnaValidation entity){

        System.out.println("Estos datos los tomo del cuerpo del Formulario");
        System.out.println("Dna :" + entity.getDna());

        try{
            return ResponseEntity.status(HttpStatus.OK).body(dnaValidationServices.save(entity));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody DnaValidation entity){
        System.out.println("EL ID LO TOMO DE LA URL");
        System.out.println("Id :" + entity.getId());
        System.out.println("Estos datos los tomo del cuerpo del Formulario");
        System.out.println("Dna :" + entity.getDna());

        try{
            return ResponseEntity.status(HttpStatus.OK).body(dnaValidationServices.update(id, entity));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dnaValidationServices.delete(id));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }

    /*
    @PostMapping("")
    public ResponseEntity<Void> isMutant(@RequestBody dnaSequenceDTO dnaSequenceDTO) {
        boolean isMutant = DnaValidationServices.isMutant(dnaSequenceDTO.getDna());
        return isMutant ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
*/
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
