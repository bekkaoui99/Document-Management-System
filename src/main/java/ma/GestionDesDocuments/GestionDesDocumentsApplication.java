package ma.GestionDesDocuments;

import ma.GestionDesDocuments.dto.AuthorDto;
import ma.GestionDesDocuments.dto.CategorieDto;
import ma.GestionDesDocuments.dto.DocumentDto;
import ma.GestionDesDocuments.repositories.DocumentRepositorie;
import ma.GestionDesDocuments.service.AuthorService;
import ma.GestionDesDocuments.service.CategorieService;
import ma.GestionDesDocuments.service.DocumentService;
import ma.GestionDesDocuments.state.LogConvertToPage;
import org.modelmapper.ModelMapper;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class GestionDesDocumentsApplication {



    public static void main(String[] args) {
        SpringApplication.run(GestionDesDocumentsApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


    @Bean
    CommandLineRunner start(DocumentRepositorie documentRepositorie, AuthorService authorService , CategorieService  categorieService , DocumentService documentService){
        return args -> {

            Stream.of("hamza","khalid","toufik","aziz").forEach(name->{

            AuthorDto authorDto = new AuthorDto();
            authorDto.setFirstName(name);
            authorDto.setLastName("tets");
            authorDto.setDescription("description tets");

            authorService.create(authorDto);

            });


            System.out.println("================================================");



        CategorieDto categorieDto = new CategorieDto();
        categorieDto.setTitre("sport");
        categorieDto.setDescription("Description sport");
        categorieService.create(categorieDto);

        CategorieDto categorieDto1 = new CategorieDto();
        categorieDto1.setTitre("dev");
        categorieDto1.setDescription("Description dev");
        categorieService.create(categorieDto1);

            CategorieDto categorieDto2 = new CategorieDto();
            categorieDto2.setTitre("machine larning");
            categorieDto2.setDescription("Description machine larning");
            categorieService.create(categorieDto2);

        categorieService.getAllCategorie().forEach(categorie -> System.out.println(categorie));


        System.out.println("**********************************");

            for (int i = 0; i < 5; i++) {
                DocumentDto documentDto = new DocumentDto();
                documentDto.setLangue("arabe");
                documentDto.setPicture("image.png");
                documentDto.setTitre("titre");
                AuthorDto authorById = authorService.getAuthorById(1);
                System.out.println("-------------------------------");
                System.out.println(authorById);
                System.out.println("-------------------------------");
                documentDto.setAuthorDto(authorById);
                CategorieDto categorieByid = categorieService.getCategorieByid(1);
                System.out.println("-------------------------------");
                System.out.println(categorieByid);
                System.out.println("-------------------------------");
                documentDto.setCategorieDto(categorieByid);
                documentService.create(documentDto);
            }


        documentService.getAllDocument().forEach(document -> System.out.println(document) );
            System.out.println("-------------------------------");

            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");


            documentService.allDocumentByAuthor(1).forEach(documentByauthor -> System.out.println(documentByauthor));


            System.out.println("******************************");
            System.out.println("******************************");

            LogConvertToPage allDocumentByTitre = documentService.getAllDocumentByTitre("", PageRequest.of(0, 5));
            List<DocumentDto> logConverterDtos = allDocumentByTitre.getLogConverterDtos();
            logConverterDtos.forEach(documentDto -> {
                        System.out.println(  documentDto.getAuthorDto().getFirstName());
                        System.out.println(  documentDto.getAuthorDto().getLastName());
                        System.out.println(  documentDto.getCategorieDto().getTitre());
                        System.out.println("====================");
                    }

            );
            System.out.println("******************************");
            System.out.println("******************************");


            documentRepositorie.findAll().forEach(document -> {
                System.out.println(document.getAuthor().getFirstName());
                System.out.println(document.getAuthor().getLastName());
                System.out.println(document.getCategorie().getTitre());
                System.out.println("=======================");


            });


        };


    }

}
