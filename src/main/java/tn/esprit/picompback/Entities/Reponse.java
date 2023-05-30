package tn.esprit.picompback.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.picompback.Entities.Enumeration.TypeReponse;

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
public class Reponse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id_reponse ;
    String contenu_reponse ;
    Date date_reponse;
    @Enumerated(EnumType.STRING)
    TypeReponse type_reponse ;
}
