package ma.GestionDesDocuments.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titre;
    private String langue;
    private String resume;
    private String image;
    private String fichier;

    @ManyToOne
    private Author author;

    @ManyToOne
    private Categorie categorie;


}
