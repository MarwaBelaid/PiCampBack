package tn.esprit.picompback.Services.BoutiqueServices;

import tn.esprit.picompback.Entities.Commande;
import tn.esprit.picompback.Entities.Enumeration.Status;

import java.util.List;

public interface ICommandeService {
    void PasserCommandeLocation(Commande c, List<Long> idProduit, Long idClient, int qty, Status status);
}
