package tn.esprit.picompback.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

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
    long id_details_activity ;
    LocalTime heure_debut ;
    LocalTime heure_fin;
    Date date ;

    int nbPlace ;
    @JsonIgnore
    @ManyToOne
    Activity activity ;

    @JsonIgnore
    @ManyToOne
    Reservation Activities_Reservation ;



}
