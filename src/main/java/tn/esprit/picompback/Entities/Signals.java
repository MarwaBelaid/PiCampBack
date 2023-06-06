package tn.esprit.picompback.Entities;



import lombok.*;
import lombok.experimental.FieldDefaults;

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
public class Signals implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id_signal ;
    String motif_signal;
    Date date_signal ;
    @ManyToOne
    Utilisateurs signal_utilisateur ;
    @ManyToOne
    Post signal_Post ;





}
