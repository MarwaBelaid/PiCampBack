package tn.esprit.picompback.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.picompback.Entities.Enumeration.TypeEquipement;

import javax.persistence.*;
import java.io.Serializable;
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
    TypeEquipement type;
    int quantité_disponible;
    @ManyToOne
    CentreCamp Equipement_CentreCamp ;
    @ManyToMany(mappedBy="Equipements", cascade = CascadeType.ALL)
    Set<Commande> Commandes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commande")
    Set<Promo> Promos ;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Feedback_Equipemet")
    Set<Feedback> Feedbacks ;


}
