package ma.GestionDesDocuments.web.server_side;


import ma.GestionDesDocuments.dto.AuthorDto;
import ma.GestionDesDocuments.dto.DocumentDto;
import ma.GestionDesDocuments.service.AuthorService;
import ma.GestionDesDocuments.service.DocumentService;
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
public class DocumentControlerACS {

    @Autowired
    private DocumentService documentService;



    @GetMapping(path = "/document")
    public String getAllDocuments(
            Model model,
            @RequestParam(name = "lastName" , defaultValue = "") String lastName ,
            @RequestParam(name = "page" , defaultValue = "0") int page ,
            @RequestParam(name = "size" , defaultValue = "6") int size

    ){
        LogConvertToPage<DocumentDto> all = documentService.getAllDocumentByTitre(lastName,PageRequest.of(page, size));
        List<DocumentDto> allDocument = all.getLogConverterDtos();
        model.addAttribute("allDocument" , allDocument);
        model.addAttribute("pages" ,new  int[all.getTotalPages() - 1] );
        model.addAttribute("totalPages" ,all.getTotalPages() - 1  );
        model.addAttribute("curentPage" , page );
        model.addAttribute("last" , 0 );
        model.addAttribute("current" ,all.getPageIndex() );


        return "document";
    }

    @GetMapping(path = "/deleteDocument")
    public String deleteDocument(
            @RequestParam(name = "id") int id
    ){
        documentService.delete(id);
        return "redirect:/document";
    }


    @GetMapping(path = "/updateDocument")
    public String updateDocument(
            Model model ,
            @RequestParam(name = "id") int id
    ){
        DocumentDto documentDto  = documentService.getDocumentById(id);
        model.addAttribute("authorDto" , documentDto);
        return "updateDocument";
    }

    @GetMapping(path = "/createDocument")
    public String createAuthor(Model model){

        model.addAttribute("documentDto" , new DocumentDto());
        return "createDocument";
    }

    @PostMapping(path = "/saveDocument")
    public String saveAuthor(Model model ,DocumentDto documentDto){
        documentService.create(documentDto);
        return "redirect:/document";
    }





}
