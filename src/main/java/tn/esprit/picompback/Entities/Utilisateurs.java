package tn.esprit.picompback.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.picompback.Entities.Enumeration.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    @Enumerated(EnumType.STRING)
    Role role_user  ;
    String adresse_user;
    String tel_user;
    Date date_naiss_user;
    byte[] photo_user;
    Date date_inscription;

}
