package tn.esprit.picompback.Repositories.CampRepos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.picompback.Entities.CentreCamp;
import tn.esprit.picompback.Entities.Reservation;
import tn.esprit.picompback.Entities.Utilisateurs;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {


    @Query("SELECT count(a) FROM Reservation a WHERE a.reservation_utilisateur.id_user = :iduser")
    int findReservationsByUtilisateur(@Param("iduser") long  iduser) ;


    @Query("SELECT a FROM Reservation a WHERE a.reservation_utilisateur.id_user = :iduser and (a.date_debut = :datedebut or a.date_fin = :datefin )")
    List<Reservation> findReservationByReservation_utilisateurAndActitvity(@Param("iduser") long  iduser, @Param("datedebut") Date datedebut, @Param("datefin") Date  datefin );


    /*
    - Récupéer toutes les activiteé par centre de camp
    - Récuper les activité disponible nb place et dates
    -- Verifier conflit heure

     */

    /*
    ************* Cycle de vie d'une reservation *******************

    ***** Client **********

    1- Bouton : Resarver -> Resarvation d'une seule Activitée.

    2- Bouton : Créer une reservation -> Resarvation d'une liste des activitées
    (Avec nuité ou pas => Ajouter oui ou non) -> ajouter les équipements de dormir .

    * => Confirmer la reservation par mail
    * => Envoyer mail pour le camp manager

    3- Bouton : Modifier une reservation.

    4- Bouton : Annuler une reservation.

    5- Bouton : Consulter une reservation.

    6- Bouton : Search selon des filtre de recherche.

    ***** Camp manager *******

    1- Bouton : Accepter une reservation => Envoyer la réponse par mail.

    2- Bouton : Refuser une reservation => Envoyer la réponse par mail.

    3- Bouton : Suivre les reservations.

    4- Bouton : Consulter une reservation.

    5- Bouton : Search selon des filtre de recherche.

    * **************

    1- Bouton : Ajouter centre de camp.

    2- Bouton : Modifier centre de camp : affecter des equipements .




    ***** Admin *******

    1- Bouton : supprimer centre de camp.




  ******************** Accueil *******************

   ***** Client **********

    * Afficher : Liste des prochaine évenement : Activity.
    * Afficher : Liste des centres des camps (Kpi historique de recherche).
    * Afficher : Liste des activitées disponible prochainement.
    * Notifier le client si il ya une nouvelle activitée
     */
}
