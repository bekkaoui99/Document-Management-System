package ma.GestionDesDocuments.repositories;

import ma.GestionDesDocuments.entities.Categorie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorieRepositorie extends JpaRepository<Categorie , Integer> {

    public List<Categorie> findByTitreContaining(String titre);
    public Page<Categorie> findByTitreContaining(String titre , Pageable pageable);
}
