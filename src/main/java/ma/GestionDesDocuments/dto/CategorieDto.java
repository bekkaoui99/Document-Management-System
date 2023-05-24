package ma.GestionDesDocuments.dto;



import lombok.*;

import java.util.ArrayList;
import java.util.List;



@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class CategorieDto {

    private Integer id;
    private String titre;
    private String description;



    private List<DocumentDto> documentDtoList = new ArrayList<>();
}
