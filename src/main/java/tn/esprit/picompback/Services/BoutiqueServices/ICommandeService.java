package tn.esprit.picompback.Services.BoutiqueServices;

import tn.esprit.picompback.Entities.Commande;
import tn.esprit.picompback.Entities.Enumeration.*;

public interface ICommandeService {
    void PasserCommande(Long idProduit, Long idClient, int qty);
    void UpdateCommande( Long idCommande,TypeCommande typeCommande, TypePaiement typePaiement, int daysLocation);

}
