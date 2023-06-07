package tn.esprit.picompback.Repositories.BoutiqueRepos;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.picompback.Entities.*;

public interface CommandeRepo extends JpaRepository<Commande,Long> {
}
