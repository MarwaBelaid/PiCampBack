package tn.esprit.picompback.Services.BoutiqueServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.picompback.Entities.*;
import tn.esprit.picompback.Entities.Enumeration.Status;
import tn.esprit.picompback.Repositories.BoutiqueRepos.*;

import java.util.Optional;


@Service
public class CommandeService {

    @Autowired
    CommandeRepo commande_repo;

    @Autowired
    ProductRepo prd_repo;

    @Override
    public String PasserCommandeLocation((Long idProduit, Long idClient, int qty, Status status) {
        Optional<Equipement> prd = prd_repo.findById(idProduit);
        Optional<Utilisateurs> util = prd_repo.findById(idProduit);

        commande_repo.save(p);
        return "commande passer avec sucée";
    }
}
