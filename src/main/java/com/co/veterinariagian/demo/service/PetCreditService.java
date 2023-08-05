package com.co.veterinariagian.demo.service;

import com.co.veterinariagian.demo.model.PetCredit;
import com.co.veterinariagian.demo.repository.PetCreditRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class PetCreditService {
    PetCreditRepository petCreditRepository;
    public PetCreditService(PetCreditRepository petCreditRepository) {
        this.petCreditRepository = petCreditRepository;
    }

    public Mono<PetCredit> findPetCreditById(Integer id){
        return petCreditRepository.findById(id);
    }
    public Flux<PetCredit> findAllPetCredits(){
        return petCreditRepository.findAll();
    }
    public Flux<PetCredit> findAllPetCreditsByActive(Boolean isActive){
        return petCreditRepository.findByIsActive(isActive);
    }
    public Mono<PetCredit> saveNewPetCredit(PetCredit petcredit){
        if (updateAndSaveValidation(petcredit)){
            return petCreditRepository.save(petcredit);
        }
        return Mono.empty();

    }
 public Mono<PetCredit> updatePetCredit(int id, PetCredit petcredit){
            return petCreditRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                    .flatMap(optionalPetCredit -> {
                        if (optionalPetCredit.isPresent()){
                            petcredit.setIdPet(id);
                            petcredit.setTotalAmount(optionalPetCredit.get().getTotalAmount());
                            petcredit.setDescription(optionalPetCredit.get().getDescription());
                            petcredit.setAmountPaid(petcredit.getAmountPaid()+ optionalPetCredit.get().getAmountPaid());
                            petcredit.setIsActive(Boolean.TRUE);
                            if((petcredit.getAmountPaid()) >= optionalPetCredit.get().getTotalAmount()){
                                petcredit.setIsActive(Boolean.FALSE);
                            }
                            return petCreditRepository.save(petcredit);
                        }
                        return Mono.empty();
                    });
    }
    public Mono<PetCredit> deletePetCreditById(int id){
        return petCreditRepository.findById(id)
                .flatMap(petCredit -> petCreditRepository.deleteById(petCredit.getIdPet()).thenReturn(petCredit));
    }
    public Mono<Void> deleteAllPetCredits() {
        return petCreditRepository.deleteAll();
    }
    public boolean updateAndSaveValidation(PetCredit petcredit){
        return petcredit.getTotalAmount() > 0 &&
                petcredit.getAmountPaid() >= 0 &&
                petcredit.getTotalAmount() > petcredit.getAmountPaid();
    }

}
