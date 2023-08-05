package com.co.veterinariagian.demo.controller;

import com.co.veterinariagian.demo.model.PetCredit;
import com.co.veterinariagian.demo.repository.PetCreditRepository;
import com.co.veterinariagian.demo.service.PetCreditService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/petcredit")
public class PetCreditController {


    PetCreditService petCreditService;

    public PetCreditController(PetCreditService petCreditService) {
        this.petCreditService = petCreditService;
    }

    @GetMapping("/getPetcreditbyid/{id}")
    public Mono<PetCredit> getPetCreditById(@PathVariable Integer id) {
        return petCreditService.findPetCreditById(id);
    }

    @GetMapping("/findallpetcredits")
    public Flux<PetCredit> findAll() {
        return petCreditService.findAllPetCredits();

    }

    @PostMapping("/save")
    public Mono<PetCredit> createPetCredit(@RequestBody PetCredit petCredit) {

        return petCreditService.saveNewPetCredit(petCredit);

    }

    @PutMapping("/update/{id}")
    public Mono<PetCredit> updatePetCredit(@PathVariable Integer id, @RequestBody PetCredit petCredit) {

        return petCreditService.updatePetCredit(id, petCredit);

    }

    @DeleteMapping("/deletepetcreditbyid/{id}")
    public Mono<PetCredit> deletePetCreditById(@PathVariable Integer id) {
        return petCreditService.deletePetCreditById(id);

    }

    @DeleteMapping("/deleteAll")
    public Mono<Void> deleteAllPetCredits() {
        return petCreditService.deleteAllPetCredits();

    }

    @GetMapping("/findbyactive/{isActive}")
    public Flux<PetCredit> getByActive(@PathVariable Boolean isActive) {
        return petCreditService.findAllPetCreditsByActive(isActive);

    }


}
