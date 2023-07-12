package tn.esprit.picompback.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.picompback.Entities.Enumeration.EtatActivityCentreCamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class DetailsActivity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idDA  ;
    String idDetailsActivity  ;
    LocalTime heure_debut ;
    LocalTime heure_fin;
    Date date ;
   /* @Enumerated(EnumType.STRING)
    EtatActivityCentreCamp etatActivity = EtatActivityCentreCamp.NonComplet;*/
    int nbPlace ;


    String etatActivity ;
    @JsonIgnore
    @ManyToOne
    Activity activity ;

  //  @JsonIgnore
    //@ManyToOne
   // Reservation Activities_Reservation ;

    //@ManyToMany(fetch = FetchType.EAGER ,mappedBy = "Activities")
    //Set<Reservation> Activities_Reservation;
    //@ManyToMany(fetch = FetchType.EAGER)

    @ManyToMany(mappedBy = "detailsActivitiesRes")
    @JsonIgnore
    Set<Reservation> reservations;



}
