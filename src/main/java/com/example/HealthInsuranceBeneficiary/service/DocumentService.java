package com.example.HealthInsuranceBeneficiary.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.HealthInsuranceBeneficiary.model.Document;
import com.example.HealthInsuranceBeneficiary.repository.DocumentRepository;

/**
 * Serviços CRUD para Document
 * 
 * Utiliza os repositorios DocumentRepository para acesso ao banco de dados
 */
@Service
public class DocumentService {
	private final DocumentRepository documentRepository;
	
	@Autowired
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }
    
    // Retorna um Document pelo ID
    public Document getDocumentById(Long id) {
        return documentRepository.findById(id).orElseThrow();
    }
    
    // Retorna todos os Documentos pelo beneficiary_id
    public List<Document> getDocumentsByBeneficiaryId(Long beneficiaryId) {
        return documentRepository.findByBeneficiaryId(beneficiaryId);
    }
    
    // Cria um novo Document
    public Document createDocument(Document document) {
        return documentRepository.save(document);
    }
    
    // Atualiza um Beneficiary pelo ID
    public Document updateDocumentById(Long id, Document updatedDocument) {
    	Document document = documentRepository.findById(id).orElseThrow();
        
    	// Atualiza os dados do benchmark com os novos dados
        BeanUtils.copyProperties(updatedDocument, document);
        
    	return documentRepository.save(document);
    }
    
    // Exclui um Beneficiary pelo ID
    @Transactional
    public void deleteDocumentById(Long id) {
    	Document document = documentRepository.findById(id).orElseThrow();
        
    	documentRepository.delete(document);
    }

}
