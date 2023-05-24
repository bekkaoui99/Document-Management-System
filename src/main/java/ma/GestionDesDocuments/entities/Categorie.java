package ma.GestionDesDocuments.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titre;
    private String description;

    @OneToMany(mappedBy = "categorie"  , cascade = CascadeType.ALL)
    private List<Document> documentList = new ArrayList<>();



}
