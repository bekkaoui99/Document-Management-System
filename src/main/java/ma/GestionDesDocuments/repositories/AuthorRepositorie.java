package ma.GestionDesDocuments.repositories;

import ma.GestionDesDocuments.entities.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepositorie extends JpaRepository<Author , Integer> {

    Page<Author> findByLastNameContains(String LastName , Pageable pageable);
    List<Author> findByLastNameContains(String LastName);


}
