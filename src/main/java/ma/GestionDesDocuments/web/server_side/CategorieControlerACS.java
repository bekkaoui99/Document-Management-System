package ma.GestionDesDocuments.web.server_side;


import ma.GestionDesDocuments.dto.AuthorDto;
import ma.GestionDesDocuments.dto.CategorieDto;
import ma.GestionDesDocuments.service.CategorieService;
import ma.GestionDesDocuments.state.LogConvertToPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CategorieControlerACS {

    @Autowired
    private CategorieService categorieService;


    @GetMapping(path = "/categories")
    public String categories(
            @RequestParam(name = "page" , defaultValue = "0") int page,
            @RequestParam(name = "size" , defaultValue = "5") int size,
            @RequestParam(name = "titre" , defaultValue = "") String titre,
            Model model
    ){

        LogConvertToPage<CategorieDto> all = categorieService.getAllCategorieByTitre(titre , PageRequest.of(page ,size));
        List<CategorieDto> allCategorie = all.getLogConverterDtos();

        model.addAttribute("allCategorie" , allCategorie);
        model.addAttribute("pages" ,new  int[all.getTotalPages() - 1] );
        model.addAttribute("totalPages" ,all.getTotalPages() - 1  );
        model.addAttribute("curentPage" , page );
        model.addAttribute("last" , 0 );
        model.addAttribute("current" ,all.getPageIndex() );
        return "categorie";
    }



    @GetMapping(path = "/deleteCategorie")
    public String deleteAuthor(
            @RequestParam(name = "id") int id
    ){
        categorieService.delete(id);
        return "redirect:/categories";
    }


    @GetMapping(path = "/updateCategorie")
    public String updateAuthor(
            Model model ,
            @RequestParam(name = "id") int id
    ){
        CategorieDto categorieByid = categorieService.getCategorieByid(id);
        model.addAttribute("categorieDto" , categorieByid);
        return "updateCategorie";
    }

    @GetMapping(path = "/createCategorie")
    public String createAuthor(Model model){

        model.addAttribute("categorieDto" , new CategorieDto());
        return "createCategorie";
    }

    @PostMapping(path = "/saveCategorie")
    public String saveCategorie(CategorieDto categorieDto){
        categorieService.create(categorieDto);
        return "redirect:/categories";
    }



}
