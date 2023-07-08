package tn.esprit.picompback.Repositories.BoutiqueRepos;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.picompback.Entities.Commande;
import tn.esprit.picompback.Entities.Utilisateurs;

public interface userCommandeRepo extends JpaRepository<Utilisateurs,Long> {
}
