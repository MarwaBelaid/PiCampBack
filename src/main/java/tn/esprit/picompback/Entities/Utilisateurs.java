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
public class Utilisateurs implements Serializable {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
long id_user ;
String nom_user ;
String prenom_user ;

String email_user ;
String mdp_user ;
String adresse_user;
String tel_user;
Date date_naiss_user;
byte[] photo_user;
Date date_inscription;

@ManyToOne
Role role_user ;

@ManyToOne
Meteo meteo ;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "post_utilisateurs")
Set<Post> Posts ;

@ManyToMany(cascade = CascadeType.ALL)
Set<Post> favoris ;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "coment_utilisateurs")
Set<Commentaire> Commentaires ;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "user_qui_a_reclamer")
Set<Reclamation> Reclamation_by_user ;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "user_qui_a_traiter_reclamation")
Set<Reclamation> Reclamation_traite ;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "feedback_utilisateur")
Set<Feedback> Feedbacks ;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "signal_utilisateur")
Set<Signals> signals ;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "reservation_utilisateur")
Set<Reservation> Reservations ;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "commande_utilisateur")
Set<Commande> Commandes ;


}
