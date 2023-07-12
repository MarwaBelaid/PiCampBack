package tn.esprit.picompback.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
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
   // @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable (name = "centre_images",
    joinColumns =  {
         @JoinColumn(name = "centre_id")},
    inverseJoinColumns =  {
            @JoinColumn(name = "image_id")})
    Set<Image> photos ;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Feedback_CentreCamp")
    Set<Feedback> Feedbacks ;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activityCentreCamp")
    Set<Activity> Activities ;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Equipement_CentreCamp")
    Set<Equipement> Equipements ;



}
