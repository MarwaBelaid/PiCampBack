package tn.esprit.picompback.Services.BoutiqueServices;

import tn.esprit.picompback.Entities.Commande;
import tn.esprit.picompback.Entities.CommandeEquipement;
import tn.esprit.picompback.Entities.Enumeration.*;
import tn.esprit.picompback.Entities.Equipement;
import tn.esprit.picompback.Entities.Utilisateurs;

import java.util.List;

public interface ICommandeService {
    void PasserCommande(Long idProduit, Long idClient, int qty);
    void UpdateCommande( Long idCommande,TypeCommande typeCommande, TypePaiement typePaiement, Integer daysLocation);

    List<CommandeEquipement> retrieveCommandesEquipement(Long idCommande);

    Commande retrieveCommandeUser(Long idUser , EtatCommande etat);

    void updateCart(Long idCommande, Long idEquiCommande, int qty);

    Utilisateurs retrieveUser(Long idUser);

}
