package ma.GestionDesDocuments.service;


import ma.GestionDesDocuments.dto.AuthorDto;
import ma.GestionDesDocuments.dto.CategorieDto;
import ma.GestionDesDocuments.dto.DocumentDto;
import ma.GestionDesDocuments.entities.Author;
import ma.GestionDesDocuments.entities.Categorie;
import ma.GestionDesDocuments.state.LogConvertToPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DocumentService {

    DocumentDto create(DocumentDto documentDto);

    List<DocumentDto> getAllDocument();

    DocumentDto getDocumentById(int id);

    List<DocumentDto> getDocumentByTitre(String titre);

    LogConvertToPage getAllDocumentByTitre(String titre , Pageable pageable);
    void delete(int id);

    DocumentDto update(int id , DocumentDto documentDto);



    List<DocumentDto> allDocumentByCategorie(int categorie_id);

    List<DocumentDto> allDocumentByAuthor(int author_id);



}
