package tn.esprit.picompback.Repositories.BoutiqueRepos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.picompback.Entities.*;

@Repository
public interface CentreCampRepo extends JpaRepository<CentreCamp,Long> {
}
