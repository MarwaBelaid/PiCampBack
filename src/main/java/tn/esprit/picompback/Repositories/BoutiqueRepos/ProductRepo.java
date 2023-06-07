package tn.esprit.picompback.Repositories.BoutiqueRepos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.picompback.Entities.Equipement;
@Repository
public interface ProductRepo extends JpaRepository<Equipement,Long> {
}
