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
public class Like implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id ;
    String nom;
//    @OneToOne
//    private Utilisateurs likeuser;
//    @ManyToOne
//    Post post;
}
