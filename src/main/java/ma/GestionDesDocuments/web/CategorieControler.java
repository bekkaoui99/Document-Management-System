package ma.GestionDesDocuments.web;


import ma.GestionDesDocuments.dto.CategorieDto;
import ma.GestionDesDocuments.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*@RestController
@RequestMapping("api/")*/
public class CategorieControler {


    @Autowired
    private CategorieService categorieService;

    @GetMapping("categories/")
    public List<CategorieDto> getAllCategories(){
        return categorieService.getAllCategorie();
    }

    @GetMapping(path = "categorie/{id}")
    public CategorieDto getCategorieById(@PathVariable(name = "id") int id){
        return categorieService.getCategorieByid(id);
    }

    @GetMapping(path = "categorie")
    public List<CategorieDto> getCategorieByTitre(@RequestParam(name = "titre") String titre){
        return categorieService.getCategorieByTitre(titre);
    }


    @PostMapping(path = "categorie")
    public CategorieDto create(@RequestBody CategorieDto categorieDto){
        return categorieService.create(categorieDto);
    }

    @PutMapping(path = "categorie/{id}")
    public CategorieDto update(@PathVariable(name = "id") int id , @RequestBody CategorieDto categorieDto){
        return categorieService.update(id , categorieDto);
    }


    @DeleteMapping(path = "categorie")
    public void delete(@PathVariable(name = "id") int id){
         categorieService.delete(id);
    }

}
