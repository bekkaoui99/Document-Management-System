package ma.GestionDesDocuments.service;



import ma.GestionDesDocuments.dto.CategorieDto;
import ma.GestionDesDocuments.state.LogConvertToPage;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface CategorieService {

    CategorieDto create(CategorieDto categorieDto);

    List<CategorieDto> getAllCategorie();

    LogConvertToPage getAllCategorie(Pageable pageable);
    LogConvertToPage getAllCategorieByTitre(String titre , Pageable pageable);


    CategorieDto getCategorieByid(int id);

    List<CategorieDto> getCategorieByTitre(String titre);

    void delete(int id);

    CategorieDto update(int id , CategorieDto categorieDto);

}
