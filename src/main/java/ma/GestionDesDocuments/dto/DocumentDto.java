package ma.GestionDesDocuments.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class DocumentDto {

    private Integer id;
    private String titre;
    private String langue;
    private String resume;
    private String picture;
    private String file;


    private AuthorDto authorDto;


    private CategorieDto categorieDto;
}
