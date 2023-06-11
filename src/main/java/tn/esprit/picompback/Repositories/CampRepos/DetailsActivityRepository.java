package tn.esprit.picompback.Repositories.CampRepos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.picompback.Entities.DetailsActivity;
import tn.esprit.picompback.Entities.Reservation;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface DetailsActivityRepository extends JpaRepository<DetailsActivity,Long> {


    // Afficher toutes les actviter et ses details disponible
    @Query("SELECT da FROM DetailsActivity da INNER JOIN da.activity activity WHERE da.activity.id_activity = activity.id_activity And da.activity.id_activity = :idActivity And da.nbPlace < activity.capacite_max ")
    Set<DetailsActivity> findDetailsActivitiesByNbPlaceLessThanCapaciteMax(@Param("idActivity") long  idActivity);

    // récupérer MaxIdDetails
    @Query("SELECT COALESCE(max(da.id),0) FROM DetailsActivity da INNER JOIN da.activity activity WHERE da.activity.id_activity = activity.id_activity And da.activity.id_activity = :idActivity ")
    int finMaxIdByActivity(@Param("idActivity") long  idActivity);


}
