package tn.esprit.picompback.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.picompback.Entities.Enumeration.EtatCommande;
import tn.esprit.picompback.Entities.Enumeration.TypeCommande;
import tn.esprit.picompback.Entities.Enumeration.TypePaiement;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
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
    LocalDate date_achat_commande;
    LocalDate date_debut_location;
    LocalDate date_fin_location;
    float montant_total ;
    int quantite_totale=0;

    @Enumerated(EnumType.STRING)
    EtatCommande etat ;
    @Enumerated(EnumType.STRING)
    TypePaiement type_paiement ;

    @Enumerated(EnumType.STRING)
    TypeCommande type_commande ;
    @ManyToOne
    @JsonIgnore
    Utilisateurs commande_utilisateur ;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipement")
    Set<Promo> Promos ;

    @OneToMany(mappedBy = "commande")
    @JsonIgnore
    private Set<CommandeEquipement> commandeEquipements;

    public Set<CommandeEquipement> getCommandeEquipements() {
        if (commandeEquipements == null) {
            commandeEquipements = new HashSet<>();
        }
        return commandeEquipements;
    }

}
