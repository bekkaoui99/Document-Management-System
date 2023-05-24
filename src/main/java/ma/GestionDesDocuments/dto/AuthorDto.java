package ma.GestionDesDocuments.dto;

import lombok.*;


import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class AuthorDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String description;

    private List<DocumentDto> documentDtoList = new ArrayList<>();
}
