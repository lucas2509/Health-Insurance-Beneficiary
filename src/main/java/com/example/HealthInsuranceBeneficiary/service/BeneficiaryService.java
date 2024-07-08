package com.example.HealthInsuranceBeneficiary.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.HealthInsuranceBeneficiary.model.Beneficiary;
import com.example.HealthInsuranceBeneficiary.repository.BeneficiaryRepository;

/**
 * Serviços CRUD para Beneficiary
 * 
 * Utiliza os repositorios beneficiaryRepository para acesso ao banco de dados
 */
@Service
public class BeneficiaryService {
	private final BeneficiaryRepository beneficiaryRepository;
	
	@Autowired
    public BeneficiaryService(BeneficiaryRepository beneficiaryRepository) {
        this.beneficiaryRepository = beneficiaryRepository;
    }
	
	// Retorna todos os beneficiaries
    public List<Beneficiary> getAllBeneficiaries() {
        return beneficiaryRepository.findAll();
    }
    
    // Retorna um Beneficiary pelo ID
    public Beneficiary getBeneficiaryById(Long id) {
        return beneficiaryRepository.findById(id).orElseThrow();
    }
    
    // Cria um novo Beneficiary
    @Transactional
    public Beneficiary createBeneficiary(Beneficiary beneficiary) {
        return beneficiaryRepository.save(beneficiary);
    }
    
    // Atualiza um Beneficiary pelo ID
    @Transactional
    public Beneficiary updateBeneficiaryById(Long id, Beneficiary updatedBeneficiary) {
    	Beneficiary beneficiary = beneficiaryRepository.findById(id).orElseThrow();
        
    	// Atualiza os dados do benchmark com os novos dados
        BeanUtils.copyProperties(updatedBeneficiary, beneficiary);
        
    	return beneficiaryRepository.save(beneficiary);
    }
    
    // Exclui um Beneficiary pelo ID
    @Transactional
    public void deleteBeneficiaryById(Long id) {
    	Beneficiary beneficiary = beneficiaryRepository.findById(id).orElseThrow();
        
    	beneficiaryRepository.delete(beneficiary);
    }

}
