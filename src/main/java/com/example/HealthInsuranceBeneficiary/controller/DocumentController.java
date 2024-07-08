package com.example.HealthInsuranceBeneficiary.controller;

import java.util.List;
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

import com.example.HealthInsuranceBeneficiary.model.Document;
import com.example.HealthInsuranceBeneficiary.service.DocumentService;

import jakarta.validation.Valid;

/**
 * Controlador para operações relacionadas a Document
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/beneficiary/document")
public class DocumentController {
	private final DocumentService documentService;
	
	public DocumentController(DocumentService documentService) {
		this.documentService = documentService;
	}
	
	//Obtem um benchmark pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocumentById(@PathVariable Long id) {
        try {
        	Document document = documentService.getDocumentById(id);
            return ResponseEntity.ok(document);
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } 
    }
    
    // Obtém todos os Documentos de Beneficiary
    @GetMapping("/documents{beneficiaryId}")
    public ResponseEntity<List<Document>> getDocumentsByBeneficiaryId(@PathVariable Long beneficiaryId) {
        List<Document> documents = documentService.getDocumentsByBeneficiaryId(beneficiaryId);
        if (documents.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(documents);
        }
    }
    
    //Cria um novo Document
    @PostMapping
    public ResponseEntity<Document> createDocument(@RequestBody @Valid Document document) {
        try {
        	Document createdDocument = documentService.createDocument(document);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDocument);
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } 
        catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }
    
    //Atualiza um Document pelo ID
    @PutMapping("/{id}")
    public ResponseEntity<Document> updateDocument(@PathVariable Long id, @RequestBody Document updatedDocument) {
        try {
        	Document updatedEntity = documentService.updateDocumentById(id, updatedDocument);
            
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
    
    //Exclui um Document pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        try {
        	documentService.deleteDocumentById(id);
            return ResponseEntity.noContent().build();
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } 
    }

}
