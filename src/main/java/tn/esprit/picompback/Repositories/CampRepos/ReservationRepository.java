package tn.esprit.picompback.Repositories.CampRepos;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.picompback.Entities.CentreCamp;
import tn.esprit.picompback.Entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

}
