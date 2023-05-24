package ma.GestionDesDocuments.web;


import ma.GestionDesDocuments.dto.AuthorDto;
import ma.GestionDesDocuments.dto.CategorieDto;
import ma.GestionDesDocuments.dto.DocumentDto;
import ma.GestionDesDocuments.service.AuthorService;
import ma.GestionDesDocuments.service.CategorieService;
import ma.GestionDesDocuments.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*@RestController
@RequestMapping("/api/")*/
public class DocumentControler {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategorieService categorieService;


    @GetMapping(path = "documents")
    public List<DocumentDto> getAllDocuments(){
        return documentService.getAllDocument();
    }

    @GetMapping(path = "document")
    public List<DocumentDto> getDocumentByTitre(@RequestParam(name = "titre") String titre){
        return documentService.getDocumentByTitre(titre);
    }

    @GetMapping(path = "document/{id}")
    public DocumentDto getDocumentById(@PathVariable(name = "id") int id){
        return documentService.getDocumentById(id);
    }


    @PostMapping(path = "author/{author_id}/categorie/{categorie_id}/document")
    public DocumentDto create(
            @RequestParam(name = "author_id") int author_id,
            @RequestParam(name = "categorie_id") int categorie_id,
            @RequestBody DocumentDto documentDto
                             )
    {
        AuthorDto authorById = authorService.getAuthorById(author_id);
        CategorieDto categorieByid = categorieService.getCategorieByid(categorie_id);
        documentDto.setAuthorDto(authorById);
        documentDto.setCategorieDto(categorieByid);
        return documentService.create(documentDto);
    }


    @PutMapping(path = "document")
    public DocumentDto update(@PathVariable(name = "id") int id , @RequestBody DocumentDto documentDto){
        return documentService.update(id , documentDto);
    }


    @DeleteMapping(path = "document")
    public void delete(@PathVariable(name = "id") int id){
        documentService.delete(id);
    }




}
