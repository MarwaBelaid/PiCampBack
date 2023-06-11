package tn.esprit.picompback.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

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

    int nbPlace ;

    @JsonIgnore
    @ManyToOne
    Activity activity ;

  //  @JsonIgnore
    //@ManyToOne
   // Reservation Activities_Reservation ;

    //@ManyToMany(fetch = FetchType.EAGER ,mappedBy = "Activities")
    //Set<Reservation> Activities_Reservation;
    //@ManyToMany(fetch = FetchType.EAGER)

    @ManyToMany(mappedBy = "detailsActivities")
    @JsonIgnore
    Set<Reservation> reservations;

}
