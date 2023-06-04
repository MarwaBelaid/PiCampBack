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
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id_post ;
    String sujet_post ;
    String contenu_post;
    Date date_creation;
    Date date_last_update;
    @ManyToOne
    Utilisateurs post_utilisateurs ;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "favoris")
    Set<Utilisateurs> utilisateursFavoris;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post_coment")
    Set<Commentaire> Commentaires ;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "signal_Post")
    Set<Signals> signals ;



}
