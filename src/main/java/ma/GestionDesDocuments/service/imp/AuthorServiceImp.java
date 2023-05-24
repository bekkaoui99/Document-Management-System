package ma.GestionDesDocuments.service.imp;

import ma.GestionDesDocuments.dto.AuthorDto;
import ma.GestionDesDocuments.entities.Author;
import ma.GestionDesDocuments.exception.ResourceNotFoundException;
import ma.GestionDesDocuments.repositories.AuthorRepositorie;
import ma.GestionDesDocuments.service.AuthorService;
import ma.GestionDesDocuments.state.LogConvertToPage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AuthorServiceImp implements AuthorService {

    @Autowired
    private AuthorRepositorie authorRepositorie;

    @Autowired
    private  ModelMapper modelMapper;



    @Override
    public AuthorDto create(AuthorDto authorDto) {
        Author author  = authorRepositorie.save(modelMapper.map(authorDto, Author.class));
        return modelMapper.map(author , AuthorDto.class);
    }

    @Override
    public List<AuthorDto> getAllAuthor() {
        return authorRepositorie.findAll()
                .stream()
                .map(author -> modelMapper.map(author , AuthorDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public LogConvertToPage<AuthorDto> getAllAuthor(Pageable pageable) {


            List<AuthorDto> listAuthor = authorRepositorie.findAll(pageable).getContent().stream()
                    .map(author -> modelMapper.map(author , AuthorDto.class)).collect(Collectors.toList());

            LogConvertToPage<AuthorDto> logConvertToPages = new LogConvertToPage<>();
            logConvertToPages.setLogConverterDtos(listAuthor);
            logConvertToPages.setPageIndex(authorRepositorie.findAll(pageable).getNumber());
            logConvertToPages.setPageSize(authorRepositorie.findAll(pageable).getSize());
            logConvertToPages.setTotalPages(authorRepositorie.findAll(pageable).getTotalPages());




            return logConvertToPages;
    }

    @Override
    public AuthorDto getAuthorById(int id) {
        Author author = authorRepositorie.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author"));
        return   modelMapper.map(author , AuthorDto.class);
    }

    @Override
    public List<AuthorDto> getAuthorByLastName(String LastName) {
        return authorRepositorie.findByLastNameContains(LastName)
                .stream()
                .map(author -> modelMapper.map(author , AuthorDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public LogConvertToPage getAuthorByLastNameContaining(String LastName , Pageable pageable) {
        List<AuthorDto> listAuthor = authorRepositorie.findByLastNameContains(LastName,pageable).getContent().stream()
                .map(author -> modelMapper.map(author , AuthorDto.class)).collect(Collectors.toList());

        LogConvertToPage<AuthorDto> logConvertToPages = new LogConvertToPage<>();
        logConvertToPages.setLogConverterDtos(listAuthor);
        logConvertToPages.setPageIndex(authorRepositorie.findAll(pageable).getNumber());
        logConvertToPages.setPageSize(authorRepositorie.findAll(pageable).getSize());
        logConvertToPages.setTotalPages(authorRepositorie.findAll(pageable).getTotalPages());




        return logConvertToPages;
    }

    @Override
    public void delete(int id) {

        Author author = authorRepositorie
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author"));

        authorRepositorie.delete(author);
    }


    @Override
    public AuthorDto update(int id, AuthorDto authorDto) {
        Author author = authorRepositorie
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author"));
        author.setId(id);
        return modelMapper.map(authorRepositorie.save(author) , AuthorDto.class);
    }
}
