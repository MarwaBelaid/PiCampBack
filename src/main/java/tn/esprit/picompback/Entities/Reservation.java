package tn.esprit.picompback.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne
    Utilisateurs reservation_utilisateur ;

   // @OneToMany(cascade = CascadeType.ALL, mappedBy = "Activities_Reservation")
    //Set<DetailsActivity> Activities ;

    //@ManyToMany(fetch = FetchType.EAGER)
    //Set<DetailsActivity> Activities;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "reservation_details",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "details_id")
    )
    Set<DetailsActivity> detailsActivities;



}
