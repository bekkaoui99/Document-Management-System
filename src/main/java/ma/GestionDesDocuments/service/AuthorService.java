package ma.GestionDesDocuments.service;


import ma.GestionDesDocuments.dto.AuthorDto;
import ma.GestionDesDocuments.state.LogConvertToPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorService {

    AuthorDto create(AuthorDto authorDto);

    List<AuthorDto> getAllAuthor();
    LogConvertToPage getAllAuthor(Pageable pageable);


    AuthorDto getAuthorById(int id);


    List<AuthorDto> getAuthorByLastName(String LastName);

    LogConvertToPage getAuthorByLastNameContaining(String LastName  , Pageable pageable);

    void delete(int id);

    AuthorDto update(int id , AuthorDto authorDto);



}
