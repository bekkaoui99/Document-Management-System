package ma.GestionDesDocuments.repositories;

import ma.GestionDesDocuments.dto.AuthorDto;
import ma.GestionDesDocuments.entities.Author;
import ma.GestionDesDocuments.entities.Categorie;
import ma.GestionDesDocuments.entities.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepositorie extends JpaRepository<Document , Integer> {

    Page<Document> findByTitreContaining(String titre , Pageable pageable);
    List<Document> findByTitreContaining(String titre );

    List<Document> findByAuthor(Author author);

    List<Document> findByCategorie(Categorie categorie);
}
