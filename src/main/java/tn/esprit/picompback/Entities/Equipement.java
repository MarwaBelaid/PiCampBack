package tn.esprit.picompback.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.picompback.Entities.Enumeration.ColorEquipement;
import tn.esprit.picompback.Entities.Enumeration.SizeEquipement;
import tn.esprit.picompback.Entities.Enumeration.StatusEquipement;
import tn.esprit.picompback.Entities.Enumeration.CategorieEquipement;

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
public class Equipement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id_equipement ;
    String nom;
    String description;
    @Enumerated(EnumType.STRING)
    CategorieEquipement categorie;

    @Enumerated(EnumType.STRING)
    StatusEquipement status;
    int quantité_disponible;
    @Column(nullable = true, length = 64)
    private String photos;

    float prix;
    @Enumerated(EnumType.STRING)
    SizeEquipement size;
    @Enumerated(EnumType.STRING)
    ColorEquipement color;

    @JsonIgnore
    @ManyToOne
    CentreCamp Equipement_CentreCamp ;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commande")
    Set<Promo> Promos ;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Feedback_Equipemet")
    Set<Feedback> Feedbacks ;
    @Transient
    public String getPhotosImagePath() {
        if (photos == null ) return null;
        return "http://localhost:8082/CampProject/images/equipment-photos/" + id_equipement + "/" + photos;
    }

}
