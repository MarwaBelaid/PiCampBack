package tn.esprit.picompback.Services.BoutiqueServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.picompback.Entities.*;
import tn.esprit.picompback.Entities.Enumeration.EtatCommande;
import tn.esprit.picompback.Entities.Enumeration.StatusEquipement;
import tn.esprit.picompback.Entities.Enumeration.TypeCommande;
import tn.esprit.picompback.Entities.Enumeration.TypePaiement;
import tn.esprit.picompback.Repositories.BoutiqueRepos.*;
import tn.esprit.picompback.Repositories.UserRepos.UtilisateurRepository;
import java.time.LocalDate;
import java.util.List;

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
    public void PasserCommande(Long idProduit, Long idClient, int qty) {
        Utilisateurs util = util_repo.findById(idClient).orElse(null);
        Equipement equipement = prd_repo.findById(idProduit).orElse(null);

        if (util != null) {
            //if commende exist
            Commande commandeExist = commande_repo.findByCommandeUtilisateurIdAndEtat(idClient, EtatCommande.EnCoursTraitement);

            if (equipement != null) {
                if (commandeExist == null) {
                    //inserer un nouveau produit dans commande_equipement
                    CommandeEquipement commandeEquipement = new CommandeEquipement();
                    if(equipement.getStatus() == StatusEquipement.disponible){
                        commandeEquipement.setEquipement(equipement);
                    }else {
                        throw new IllegalArgumentException("Equipement non disponible" );
                    }
                    Commande c = new Commande();
                    c.setCommande_utilisateur(util);
                    c.setDate_achat_commande(null);
                    c.setDate_debut_location(null);
                    c.setDate_fin_location(null);
                    c.getCommandeEquipements().add(commandeEquipement);
                    c.setQuantite_totale(qty);
                    c.setEtat(EtatCommande.EnCoursTraitement);
                    c.setMontant_total(equipement.getPrix());
                    c.setType_commande(null);
                    c.setType_paiement(null);
                    commandeEquipement.setCommande(c);
                    commande_repo.save(c);
                    commande_equipement_repo.save(commandeEquipement);

                }else{
                    //ajouter quantiter to commande_equipement si le meme produit existe
                    CommandeEquipement commandeEquipement = commande_equipement_repo.findCommandeEquipemet(commandeExist.getId_commande(),idProduit);
                    int totale_qty = commandeEquipement.getQuantite_produit()+qty;
                    commandeEquipement.setQuantite_produit(totale_qty);
                    commandeEquipement.setCommande(commandeExist);
                    //update ligne commande_equipement si le meme produit existe
                    commande_equipement_repo.save(commandeEquipement);
                }
            } else {
                throw new IllegalArgumentException("Equipement with ID " + idProduit + " does not exist");
            }


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
