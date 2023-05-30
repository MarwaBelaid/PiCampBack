package tn.esprit.picompback.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.picompback.Entities.Enumeration.Statut;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Reclamation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id_reclamation ;

    String objet_reclamation ;

    String détails_reclamation;

    @Enumerated(EnumType.STRING)
    Statut statut_reclamation;


}
