package com.example.HealthInsuranceBeneficiary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.HealthInsuranceBeneficiary.model.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
	
	List<Document> findByBeneficiaryId(Long beneficiaryId);
}
