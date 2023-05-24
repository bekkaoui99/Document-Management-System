package ma.GestionDesDocuments.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Author {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String description;

    @OneToMany(mappedBy = "author" , cascade = CascadeType.ALL)
    private List<Document> documentList = new ArrayList<>();



}
