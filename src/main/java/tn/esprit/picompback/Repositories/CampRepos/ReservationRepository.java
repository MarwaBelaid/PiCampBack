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
    - Récupéer toutes les activiter par centre de camp
    - Récuper les activité disponible nb place et dates
    -- Verifier conflit heure

     */
}
