package tn.esprit.picompback.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name="users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email_user")
})
public class Utilisateurs implements Serializable {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
long id_user ;
@NotBlank
@Size(max = 20)
String username;
@NotBlank
@Size(max = 50)
String email_user ;
@NotBlank
@Size(max = 20)
String password_user;
String adresse_user;
String tel_user;
Date date_naiss_user;
byte[] photo_user;
Date date_inscription;


@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
private Set<Role> roles = new HashSet<>();



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
