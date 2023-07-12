package tn.esprit.picompback.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.picompback.Entities.Enumeration.EtatActivityCentreCamp;
import tn.esprit.picompback.Entities.Enumeration.TypeActivity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
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
    long id_activity;
    String nom_activity;
    String description;
    float prix;
    int capacite_min;
    int capacite_max;
    int age_min = 0;
    @Enumerated(EnumType.STRING)
    TypeActivity type;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "activity_images",
            joinColumns = {
                    @JoinColumn(name = "Activity_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "image_id")})
    Set<Image> photos;

    @JsonIgnore
    @ManyToOne
    CentreCamp activityCentreCamp;
/*
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activity")
    Set<DetailsActivity> detailsActivity;
*/

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
    Set<DetailsActivity> detailsActivity ;// = new HashSet<>();
}