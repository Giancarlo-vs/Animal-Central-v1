package com.co.veterinariagian.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class PetCredit {
    @Id
    private Integer idPet;
    private Boolean isActive;
    private Double totalAmount;
    private Double amountPaid;
    private String description;

}
