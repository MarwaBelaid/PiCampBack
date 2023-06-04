package tn.esprit.picompback.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.picompback.Entities.Enumeration.EtatCommande;
import tn.esprit.picompback.Entities.Enumeration.TypePaiement;

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
public class Commande implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id_commande ;
    Date date_commande;
    float montant_total ;
    @Enumerated(EnumType.STRING)
    EtatCommande etat ;
    @Enumerated(EnumType.STRING)
    TypePaiement type_paiement ;
    @ManyToOne
    Utilisateurs commande_utilisateur ;
    @ManyToMany(cascade = CascadeType.ALL)
    Set<Equipement> Equipements;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipement")
    Set<Promo> Promos ;


}
