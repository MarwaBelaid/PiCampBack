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
public class Commentaire implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id_commentaire ;

    String contenu_commentaire ;
    boolean liked;
    Date date_creation ;

    Date date_last_update;
    public void setLike(boolean liked) {
        this.liked = liked;
    }
    @ManyToOne

    Utilisateurs coment_utilisateurs ;
    @ManyToOne
    @JsonIgnore
    Post post_coment ;

    @ManyToOne
    @JsonIgnore
    Commentaire Response;

    @OneToMany(mappedBy = "Response", cascade = CascadeType.ALL)
    Set<Commentaire> Responses ;


}
