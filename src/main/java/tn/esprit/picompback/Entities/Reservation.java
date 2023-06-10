package tn.esprit.picompback.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.picompback.Entities.Enumeration.Statut;

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
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id_reservation ;
    Date date_debut ;
    Date date_fin;
    int nbr_personne;
    float montant_reservation;
    @Enumerated(EnumType.STRING)
    Statut statut ;

    @ManyToOne
    Utilisateurs reservation_utilisateur ;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Activities_Reservation")
    Set<DetailsActivity> Activities ;




}
