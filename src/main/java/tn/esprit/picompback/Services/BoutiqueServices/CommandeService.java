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
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Service
public class CommandeService implements ICommandeService {

    @Autowired
    CommandeRepo commande_repo;

    @Autowired
    userCommandeRepo user_repo;
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
                    c.setMontant_total(equipement.getPrix() * qty);
                    c.setType_commande(null);
                    c.setType_paiement(null);
                    commandeEquipement.setCommande(c);
                    commandeEquipement.setQuantite_produit(qty);
                    commande_repo.save(c);
                    commande_equipement_repo.save(commandeEquipement);

                }else{


                    CommandeEquipement commandeEquipement = commande_equipement_repo.findCommandeEquipemet(commandeExist.getId_commande(),idProduit);
                    //ajouter commande_equipement si un produit est ajouter au commande
                    if(commandeEquipement ==null){

                        CommandeEquipement cmdEq = new CommandeEquipement();
                        if(equipement.getStatus() == StatusEquipement.disponible){
                            cmdEq.setEquipement(equipement);
                        }else {
                            throw new IllegalArgumentException("Equipement non disponible" );
                        }
                        //upadate totale commande
                        float recent_total_commande = commandeExist.getMontant_total();
                        commandeExist.setMontant_total(equipement.getPrix() * qty + recent_total_commande);
                        commande_repo.save(commandeExist);

                        cmdEq.setCommande(commandeExist);
                        cmdEq.setQuantite_produit(qty);
                        commande_equipement_repo.save(cmdEq);

                    }else{
                        int totale_qty = commandeEquipement.getQuantite_produit()+qty;
                        commandeEquipement.setQuantite_produit(totale_qty);
                        commandeEquipement.setCommande(commandeExist);
                        //upadate totale commande
                        float recent_total_commande = commandeExist.getMontant_total();
                        commandeExist.setMontant_total(equipement.getPrix() * qty + recent_total_commande);
                        commande_repo.save(commandeExist);

                        //update ligne commande_equipement si le meme produit existe
                        commande_equipement_repo.save(commandeEquipement);
                    }

                }
            } else {
                throw new IllegalArgumentException("Equipement with ID " + idProduit + " does not exist");
            }


        } else {
            throw new IllegalArgumentException("Utilisateur with ID " + idClient + " does not exist");
        }
    }

    @Override
    public void UpdateCommande(Long idCommande,TypeCommande typeCommande, TypePaiement typePaiement, Integer daysLocation) {
        Commande c = commande_repo.findById(idCommande).orElse(null);
        logger.info("*************************//////////////////////////////////////In method " + typeCommande + " : "+ typePaiement);

        if (c != null) {
            if(typePaiement == TypePaiement.Espèce) {
                c.setType_paiement(TypePaiement.Espèce);
            }else {
                c.setType_paiement(TypePaiement.CarteBancaire);
            }

            if(typeCommande == TypeCommande.Location){
                c.setType_commande(TypeCommande.Location);
                c.setDate_achat_commande(null);
                c.setDate_debut_location(LocalDate.now());
                c.setDate_fin_location(LocalDate.now().plusDays(daysLocation));
            }else{
                c.setType_commande(TypeCommande.Achat);
                c.setDate_achat_commande(LocalDate.now());
                c.setDate_debut_location(null);
                c.setDate_fin_location(null);
            }
            c.setEtat(EtatCommande.Passée);
            commande_repo.save(c);
        }else {
            throw new IllegalArgumentException("Commande introuvable ");
        }
    }

    @Override
    public List<CommandeEquipement> retrieveCommandesEquipement(Long idCommande){
        return commande_equipement_repo.getCommandeEquipemet(idCommande);
    }

    @Override
    public Commande retrieveCommandeUser(Long idUser, EtatCommande etat){
        Commande c = commande_repo.findByCommandeUtilisateurIdAndEtat(idUser, etat);
        return c;
    }

    @Override
    public void updateCart(Long idCommande, Long idEquiCommande, int qty){
        CommandeEquipement ce = commande_equipement_repo.findById(idEquiCommande).orElse(null);
        if(ce !=null){
            ce.setQuantite_produit(qty);
            commande_equipement_repo.save(ce);
            Commande c = commande_repo.findById(idCommande).orElse(null);
            if(c != null){
                Set<CommandeEquipement> commEqui = c.getCommandeEquipements();
                float totalCommande =0;
                for (CommandeEquipement ceq : commEqui) {
                    // Calculate the total for the commande based on the updated CommandeEquipements
                    totalCommande += ceq.getQuantite_produit() * ceq.getEquipement().getPrix();
                }

                c.setMontant_total(totalCommande);
                commande_repo.save(c);
            }else {
                throw new IllegalArgumentException("Commande introuvable ");
            }

        }else {
            throw new IllegalArgumentException("Commande Equipement introuvable ");
        }

    }

    @Override
    public Utilisateurs retrieveUser(Long idUser){
        return user_repo.findById(idUser).orElse(null);
    }
    
}
