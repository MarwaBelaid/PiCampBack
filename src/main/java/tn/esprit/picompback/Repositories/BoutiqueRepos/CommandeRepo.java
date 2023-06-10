package tn.esprit.picompback.Repositories.BoutiqueRepos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.picompback.Entities.*;
import tn.esprit.picompback.Entities.Enumeration.EtatCommande;

public interface CommandeRepo extends JpaRepository<Commande,Long> {
    @Query("SELECT c FROM Commande c WHERE c.commande_utilisateur.id_user = :clientId AND c.etat = :etatCommande")
    Commande findByCommandeUtilisateurIdAndEtat(@Param("clientId") Long clientId, @Param("etatCommande") EtatCommande etatCommande);

}
