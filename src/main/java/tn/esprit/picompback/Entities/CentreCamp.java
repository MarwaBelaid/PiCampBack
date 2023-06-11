package tn.esprit.picompback.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class CentreCamp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id_centre ;
    String nom_centre;
    String adresse_centre;
    String tel_centre;
    String email_centre;
    float tarif_nuitee;
    int places_disponibles;
    Byte[] photos_centre ;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Feedback_CentreCamp")
    Set<Feedback> Feedbacks ;
    //@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Activity_CentreCamp")
    Set<Activity> Activities ;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Equipement_CentreCamp")
    Set<Equipement> Equipements ;



}
