package tn.esprit.picompback.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.picompback.Entities.Enumeration.role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Role implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id_role ;
    @Enumerated(EnumType.STRING)
    role role_user  ;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role_user")
    Set<Utilisateurs> utilisateurs ;
}
