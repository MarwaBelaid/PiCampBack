package tn.esprit.picompback.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
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

    @Column( length = 10000)
    //@Lob
    String photos_centre ;
    //@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Feedback_CentreCamp")
    Set<Feedback> Feedbacks ;
    //@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Activity_CentreCamp")
    Set<Activity> Activities ;
    //@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Equipement_CentreCamp")
    Set<Equipement> Equipements ;



}
