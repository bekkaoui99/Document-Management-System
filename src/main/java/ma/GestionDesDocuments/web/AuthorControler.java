package ma.GestionDesDocuments.web;


import ma.GestionDesDocuments.dto.AuthorDto;
import ma.GestionDesDocuments.entities.Author;
import ma.GestionDesDocuments.service.AuthorService;
import ma.GestionDesDocuments.state.LogConvertToPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorControler {


    @Autowired
    private AuthorService authorService;


    @GetMapping(path = "/authors")
    public List<AuthorDto> getAll(){
        return authorService.getAllAuthor();
    }

    @GetMapping(path = "/author/{id}")
    public AuthorDto getByid(@PathVariable(name = "id") int id){
        return authorService.getAuthorById(id);
    }

    @GetMapping(path = "/author/sersh")
    public List<AuthorDto> getAuthorByLastName(@RequestParam(name = "lastName" ) String lastName){
        return authorService.getAuthorByLastName(lastName);
    }


    @GetMapping(path = "/author")
    public List<AuthorDto> getAllAuthorByPage(
            @RequestParam(name = "page" , defaultValue = "0") int page,
            @RequestParam(name = "size" , defaultValue = "2") int size
                                              )
    {

        LogConvertToPage allAuthor = authorService.getAllAuthor(PageRequest.of(page, size));

        return allAuthor.getLogConverterDtos();

    }

    @DeleteMapping(path = "/author/{id}")
    public void delete(@PathVariable int id){

        authorService.delete(id);
    }

    @PutMapping(path = "/author/{id}")
    public AuthorDto update(@PathVariable int id , @RequestBody AuthorDto authorDto){
        return authorService.update(id , authorDto);
    }





}

