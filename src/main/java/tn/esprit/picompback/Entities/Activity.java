package tn.esprit.picompback.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.picompback.Entities.Enumeration.TypeActivity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
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
public class Activity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id_activity ;
    String nom_activity;
    String description ;
    float prix ;
    int capacite_min ;
    int capacite_max ;
    int age_min = 0 ;
    @Enumerated(EnumType.STRING)
    TypeActivity type ;
    Byte[] photo ;
    @JsonIgnore
    @ManyToOne
    CentreCamp Activity_CentreCamp ;


   //@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activity")
    Set<DetailsActivity> DetailsActivity ;


}
