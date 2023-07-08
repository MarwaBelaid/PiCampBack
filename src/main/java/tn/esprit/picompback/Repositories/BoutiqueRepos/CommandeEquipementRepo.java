package tn.esprit.picompback.Repositories.BoutiqueRepos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.picompback.Entities.Commande;
import tn.esprit.picompback.Entities.CommandeEquipement;
import tn.esprit.picompback.Entities.Enumeration.EtatCommande;

import java.util.List;

public interface CommandeEquipementRepo extends JpaRepository<CommandeEquipement,Long> {

    @Query("SELECT c FROM CommandeEquipement c WHERE c.commande.id_commande = :commandeId AND c.equipement.id_equipement = :equipementId")
    CommandeEquipement findCommandeEquipemet(@Param("commandeId") Long commandeId, @Param("equipementId") Long equipementId);

    @Query("SELECT c FROM CommandeEquipement c WHERE c.commande.id_commande = :commandeId ")
    List<CommandeEquipement> getCommandeEquipemet(@Param("commandeId") Long commandeId);
}
