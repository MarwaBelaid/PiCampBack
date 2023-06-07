package tn.esprit.picompback.Services.BoutiqueServices;

import tn.esprit.picompback.Entities.Enumeration.Status;

public interface ICommandeService {
    void PasserCommandeLocation(Long idProduit, Long idClient, int qty, Status status);
}
