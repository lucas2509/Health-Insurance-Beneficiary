package com.example.HealthInsuranceBeneficiary.controller;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.example.HealthInsuranceBeneficiary.model.Beneficiary;
import com.example.HealthInsuranceBeneficiary.service.BeneficiaryService;

import jakarta.validation.Valid;

/**
 * Controlador para operações relacionadas a Beneficiary
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/beneficiary")
public class BeneficiaryController {
	private final BeneficiaryService beneficiaryService;
	
	public BeneficiaryController(BeneficiaryService beneficiaryService) {
		this.beneficiaryService = beneficiaryService;
	}
	
	//Obtem um Beneficiary pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Beneficiary> getBeneficiaryById(@PathVariable Long id) {
        try {
        	Beneficiary beneficiary = beneficiaryService.getBeneficiaryById(id);
            return ResponseEntity.ok(beneficiary);
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } 
    }
    
    //Cria um novo Beneficiary
    @PostMapping
    public ResponseEntity<Beneficiary> createBeneficiary(@RequestBody @Valid Beneficiary beneficiary) {
        try {
        	Beneficiary createdBeneficiary = beneficiaryService.createBeneficiary(beneficiary);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBeneficiary);
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } 
        catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }
    
    //Atualiza um Beneficiary pelo ID
    @PutMapping("/{id}")
    public ResponseEntity<Beneficiary> updateBeneficiary(@PathVariable Long id, @RequestBody Beneficiary updatedBeneficiary) {
        try {
        	Beneficiary updatedEntity = beneficiaryService.updateBeneficiaryById(id, updatedBeneficiary);
            
            if (updatedEntity != null) return ResponseEntity.ok(updatedEntity); 
            else return ResponseEntity.notFound().build(); 
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } 
        catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }
    
   //Exclui um Beneficiary pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeneficiary(@PathVariable Long id) {
        try {
        	beneficiaryService.deleteBeneficiaryById(id);
            return ResponseEntity.noContent().build();
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } 
    }

}
