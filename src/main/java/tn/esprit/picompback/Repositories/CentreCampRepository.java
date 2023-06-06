package tn.esprit.picompback.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.picompback.Entities.CentreCamp;

@Repository
public interface CentreCampRepository extends JpaRepository<CentreCamp,Long> {
}
