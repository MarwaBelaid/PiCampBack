package tn.esprit.picompback.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.picompback.Entities.Enumeration.StatusEquipement;
import tn.esprit.picompback.Entities.Enumeration.CategorieEquipement;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Equipement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id_equipement ;
    String nom;
    String description;
    @Enumerated(EnumType.STRING)
    CategorieEquipement type;

    @Enumerated(EnumType.STRING)
    StatusEquipement status;
    int quantité_disponible;

    float prix;

    Date max_date_location;
    @ManyToOne
    CentreCamp Equipement_CentreCamp ;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commande")
    Set<Promo> Promos ;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Feedback_Equipemet")
    Set<Feedback> Feedbacks ;


}
