package tn.esprit.picompback.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@JsonIgnore
Role role_user ;

@ManyToOne
@JsonIgnore
Meteo meteo ;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "post_utilisateurs")
@JsonIgnore
Set<Post> Posts ;

@ManyToMany(cascade = CascadeType.ALL)
@JsonIgnore
Set<Post> favoris ;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "coment_utilisateurs")
        @JsonIgnore
Set<Commentaire> Commentaires ;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "user_qui_a_reclamer")
@JsonIgnore
Set<Reclamation> Reclamation_by_user ;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "user_qui_a_traiter_reclamation")
@JsonIgnore
Set<Reclamation> Reclamation_traite ;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "feedback_utilisateur")
@JsonIgnore
Set<Feedback> Feedbacks ;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "signal_utilisateur")
@JsonIgnore
Set<Signals> signals ;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "reservation_utilisateur")
@JsonIgnore
Set<Reservation> Reservations ;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "commande_utilisateur")
@JsonIgnore
Set<Commande> Commandes ;

//@OneToOne(mappedBy="likeuser")
//private Like like;


}
