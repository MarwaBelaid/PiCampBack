package tn.esprit.picompback.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Feedback implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id_feedback ;
    int nb_etoiles ;

    @ManyToOne
    Utilisateurs feedback_utilisateur;
    @ManyToOne
    CentreCamp Feedback_CentreCamp ;
    @ManyToOne
    Equipement Feedback_Equipemet ;

}
