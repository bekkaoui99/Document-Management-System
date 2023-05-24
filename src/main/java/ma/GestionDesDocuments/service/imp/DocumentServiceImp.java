package ma.GestionDesDocuments.service.imp;

import ma.GestionDesDocuments.dto.AuthorDto;
import ma.GestionDesDocuments.dto.CategorieDto;
import ma.GestionDesDocuments.dto.DocumentDto;
import ma.GestionDesDocuments.entities.Author;
import ma.GestionDesDocuments.entities.Categorie;
import ma.GestionDesDocuments.entities.Document;
import ma.GestionDesDocuments.exception.ResourceNotFoundException;
import ma.GestionDesDocuments.repositories.DocumentRepositorie;
import ma.GestionDesDocuments.service.AuthorService;
import ma.GestionDesDocuments.service.CategorieService;
import ma.GestionDesDocuments.service.DocumentService;
import ma.GestionDesDocuments.state.LogConvertToPage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImp implements DocumentService {

    @Autowired
    private DocumentRepositorie documentRepositorie;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategorieService categorieService;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public DocumentDto create(DocumentDto documentDto) {
        Document createdDocument = documentRepositorie.save(modelMapper.map(documentDto, Document.class));
        return modelMapper.map(createdDocument , DocumentDto.class);
    }


    public DocumentDto map(Document document)
    {

        DocumentDto documentDto = new DocumentDto();

        documentDto.setId(document.getId());

        documentDto.setTitre(document.getTitre());
        documentDto.setLangue(document.getLangue());
        documentDto.setResume(document.getResume());
        documentDto.setPicture(document.getImage());
        documentDto.setFile(document.getFichier());

        documentDto.setAuthorDto(modelMapper.map(document.getAuthor() , AuthorDto.class));
        documentDto.setCategorieDto(modelMapper.map(document.getCategorie() , CategorieDto.class));

        return documentDto;
    }
    @Override
    public List<DocumentDto> getAllDocument() {
        return documentRepositorie.findAll()
                .stream()
                .map(document -> map(document))
                .collect(Collectors.toList());




    }

    @Override
    public DocumentDto getDocumentById(int id) {
        Document document = documentRepositorie
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document"));
        return map(document);
    }

    @Override
    public List<DocumentDto> getDocumentByTitre(String titre) {
        return documentRepositorie
                .findByTitreContaining(titre)
                .stream()
                .map(document -> map(document))
                .collect(Collectors.toList());
    }

    @Override
    public LogConvertToPage getAllDocumentByTitre(String titre , Pageable pageable) {


        List<DocumentDto> listDocuments = documentRepositorie
                                        .findByTitreContaining(titre , pageable)
                                        .stream()
                                        .map(document -> map(document))
                                        .collect(Collectors.toList());

        LogConvertToPage<DocumentDto> logConvertToPages = new LogConvertToPage<>();
        logConvertToPages.setLogConverterDtos(listDocuments);
        logConvertToPages.setPageIndex(documentRepositorie.findAll(pageable).getNumber());
        logConvertToPages.setPageSize(documentRepositorie.findAll(pageable).getSize());
        logConvertToPages.setTotalPages(documentRepositorie.findAll(pageable).getTotalPages());




        return logConvertToPages;


    }

    @Override
    public void delete(int id) {
        Document document = documentRepositorie
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document"));
        documentRepositorie.delete(document);
    }

    @Override
    public DocumentDto update(int id, DocumentDto documentDto) {
        Document document = documentRepositorie
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document"));
        document.setId(id);
        Document created = documentRepositorie.save(document);
        return map(created);
    }

    @Override
    public List<DocumentDto> allDocumentByCategorie(int categorie_id) {

        CategorieDto categorieByid = categorieService.getCategorieByid(categorie_id);

        List<Document> documentByCategorie = documentRepositorie.findByCategorie(modelMapper.map(categorieByid, Categorie.class));

        return documentByCategorie
                .stream()
                .map(documentToDto -> map(documentToDto) )
                .collect(Collectors.toList());
    }

    @Override
    public List<DocumentDto> allDocumentByAuthor(int author_id) {

        AuthorDto authorById = authorService.getAuthorById(author_id);

        List<Document> documentByAuthor = documentRepositorie.findByAuthor(modelMapper.map(authorById, Author.class));
        return documentByAuthor
                .stream()
                .map(documentToDto -> map(documentToDto))
                .collect(Collectors.toList());
    }
}
