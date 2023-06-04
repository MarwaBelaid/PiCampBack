package tn.esprit.picompback.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.picompback.Entities.Enumeration.TypeActivity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Activity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id_activity ;
    String nom_activity;
    String description ;
    float prix ;
    Time heure_debut ;
    Time heure_fin;
    Date date ;
    int capacite_min ;
    int capacite_max ;
    int age_min ;
    @Enumerated(EnumType.STRING)
    TypeActivity type ;
    Byte[] photo ;
    @ManyToOne
    CentreCamp Activity_CentreCamp ;



}
