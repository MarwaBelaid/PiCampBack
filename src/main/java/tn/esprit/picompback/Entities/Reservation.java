package tn.esprit.picompback.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.picompback.Entities.Enumeration.Statut;

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

    @ManyToOne
    CentreCamp Reservation_CentreCamp ;




}
