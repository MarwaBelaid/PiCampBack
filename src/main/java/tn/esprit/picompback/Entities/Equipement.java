package tn.esprit.picompback.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.picompback.Entities.Enumeration.TypeEquipement;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Equipement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id_equipement ;
    String nom;
    String description;
    @Enumerated(EnumType.STRING)
    TypeEquipement type;
    int quantité_disponible;

}
