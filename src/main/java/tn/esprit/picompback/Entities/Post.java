package tn.esprit.picompback.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
    long id ;
    String sujet ;
    String contenu;
    Date date_creation;
    Date date_last_update;
    @Column(nullable = true, length = 64)
    private String imagePath; // Ajoutez cette ligne pour représenter le chemin de l'image

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @ManyToOne
    Utilisateurs post_utilisateurs ;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "favoris")
    Set<Utilisateurs> utilisateursFavoris;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post_coment")
    Set<Commentaire> Commentaires ;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "signal_Post")
    Set<Signals> signals ;
    @Transient
    public String getPhotosImagePath() {
        if (imagePath == null ) return null;
        return "http://localhost:8082/CampProject/images/posts/" + id + "/" + imagePath;
    }


}
