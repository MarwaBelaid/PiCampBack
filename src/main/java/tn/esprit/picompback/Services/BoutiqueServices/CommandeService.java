package tn.esprit.picompback.Services.BoutiqueServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.picompback.Entities.*;
import tn.esprit.picompback.Entities.Enumeration.EtatCommande;
import tn.esprit.picompback.Entities.Enumeration.TypeCommande;
import tn.esprit.picompback.Entities.Enumeration.TypePaiement;
import tn.esprit.picompback.Repositories.BoutiqueRepos.*;
import tn.esprit.picompback.Repositories.UserRepos.UtilisateurRepository;
import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Service
public class CommandeService implements ICommandeService {

    @Autowired
    CommandeRepo commande_repo;

    @Autowired
    UtilisateurRepository util_repo;
    @Autowired
    CommandeEquipementRepo commande_equipement_repo;

    @Autowired
    ProductRepo prd_repo;
    private static final Logger logger = LogManager.getLogger(CommandeService.class);

    @Override
    public void PasserCommande(Commande c, Long idProduit, Long idClient, int qty) {
        Utilisateurs util = util_repo.findById(idClient).orElse(null);

        if (util != null) {
            c.setCommande_utilisateur(util);

            CommandeEquipement commandeEquipement = new CommandeEquipement();
            commandeEquipement.setCommande(c);

            Equipement equipement = prd_repo.findById(idProduit).orElse(null);
            if (equipement != null) {
                commandeEquipement.setEquipement(equipement);
            } else {
                throw new IllegalArgumentException("Equipement with ID " + idProduit + " does not exist");
            }

            c.setDate_achat_commande(null);
            c.setDate_debut_location(null);
            c.setDate_fin_location(null);

            commandeEquipement.setQuantite_produit(qty);

            c.getCommandeEquipements().add(commandeEquipement);
            c.setQuantite_totale(qty);
            c.setEtat(EtatCommande.EnCoursTraitement);
            c.setMontant_total(equipement.getPrix());
            c.setType_commande(null);
            c.setType_paiement(null);

            commande_repo.save(c);
            commande_equipement_repo.save(commandeEquipement);
        } else {
            throw new IllegalArgumentException("Utilisateur with ID " + idClient + " does not exist");
        }
    }

    @Override
    public void UpdateCommande(Long idCommande,TypeCommande typeCommande, TypePaiement typePaiement, int daysLocation) {
        Commande c = commande_repo.findById(idCommande).orElse(null);

        if (c != null) {
            c.setType_commande(typeCommande);
            c.setType_paiement(typePaiement);

            if(typeCommande == TypeCommande.Location){
                c.setDate_achat_commande(null);
                c.setDate_debut_location(LocalDate.now());
                c.setDate_fin_location(LocalDate.now().plusDays(daysLocation));
            }else{
                c.setDate_achat_commande(LocalDate.now());
                c.setDate_debut_location(null);
                c.setDate_fin_location(null);
            }
            commande_repo.save(c);
        }else {
            throw new IllegalArgumentException("Commande introuvable ");
        }
    }
}
