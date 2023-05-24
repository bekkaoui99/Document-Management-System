package ma.GestionDesDocuments.web.server_side;

import ma.GestionDesDocuments.dto.AuthorDto;
import ma.GestionDesDocuments.service.AuthorService;
import ma.GestionDesDocuments.state.LogConvertToPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AuthorControlerACS {



    @Autowired
    private AuthorService authorService;


    @GetMapping(path = "/")
    public String index(){
        return "redirect:/index";
    }

    @GetMapping(path = "/index")
    public String home(){
        return "index";
    }



    @GetMapping(path = "/authors")
    public String getAllAuthors(
                                Model model,
                                @RequestParam(name = "lastName" , defaultValue = "") String lastName ,
                                @RequestParam(name = "page" , defaultValue = "0") int page ,
                                @RequestParam(name = "size" , defaultValue = "5") int size

            ){
        LogConvertToPage<AuthorDto> all = authorService.getAuthorByLastNameContaining(lastName,PageRequest.of(page, size));
        List<AuthorDto> allAuthor = all.getLogConverterDtos();
        model.addAttribute("allAuthor" , allAuthor);
        model.addAttribute("pages" ,new  int[all.getTotalPages() - 1] );
        model.addAttribute("totalPages" ,all.getTotalPages() - 1  );
        model.addAttribute("curentPage" , page );
        model.addAttribute("last" , 0 );
        model.addAttribute("current" ,all.getPageIndex() );


        return "author";
    }

    @GetMapping(path = "/deleteAuthor")
    public String deleteAuthor(
            @RequestParam(name = "id") int id
    ){
        authorService.delete(id);
        return "redirect:/authors";
    }


    @GetMapping(path = "/updateAuthor")
    public String updateAuthor(
            Model model ,
            @RequestParam(name = "id") int id
    ){
        AuthorDto authorById = authorService.getAuthorById(id);
        model.addAttribute("authorDto" , authorById);
        return "updateAuthor";
    }

    @GetMapping(path = "/createAuthor")
    public String createAuthor(Model model){

        model.addAttribute("authorDto" , new AuthorDto());
        return "createAuthor";
    }

    @PostMapping(path = "/saveAuthor")
    public String saveAuthor(Model model ,AuthorDto authorDto){
        authorService.create(authorDto);
        return "redirect:/authors";
    }
}
