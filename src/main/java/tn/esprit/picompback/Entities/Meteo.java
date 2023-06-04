package tn.esprit.picompback.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Meteo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id_meteo;
    Date horodatage;
    float temperature;
    String lieu ;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "meteo")
    Set<Utilisateurs> utilisateurs ;
}
