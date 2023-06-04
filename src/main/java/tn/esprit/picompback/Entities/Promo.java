package tn.esprit.picompback.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Promo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id_promo ;
    String titre;
    String description;
    Date dateDebut;
    Date dateFin;
    double pourcentagePromo;
    double codePromo;
    @ManyToOne
    Equipement equipement;
    @ManyToOne
    Commande commande;


}
