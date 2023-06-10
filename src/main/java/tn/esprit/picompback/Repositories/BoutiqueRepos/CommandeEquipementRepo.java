package tn.esprit.picompback.Repositories.BoutiqueRepos;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.picompback.Entities.Commande;
import tn.esprit.picompback.Entities.CommandeEquipement;

public interface CommandeEquipementRepo extends JpaRepository<CommandeEquipement,Long> {
}
