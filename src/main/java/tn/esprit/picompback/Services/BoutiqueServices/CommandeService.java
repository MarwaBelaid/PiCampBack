package tn.esprit.picompback.Services.BoutiqueServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.picompback.Entities.*;
import tn.esprit.picompback.Entities.Enumeration.Status;
import tn.esprit.picompback.Repositories.BoutiqueRepos.*;
import tn.esprit.picompback.Repositories.UserRepos.UtilisateurRepository;

import java.util.List;
import java.util.Optional;


@Service
public class CommandeService implements ICommandeService{

    @Autowired
    CommandeRepo commande_repo;

    @Autowired
    UtilisateurRepository util_repo;

    @Autowired
    ProductRepo prd_repo;

    @Override
    public void PasserCommandeLocation(Commande c, List<Long> idProduit, Long idClient, int qty, Status status){


        //Equipement prd = prd_repo.findById(idProduit).get();
        Utilisateurs util = util_repo.findById(idClient).get();
        if (util != null) {
            c.setCommande_utilisateur(util);
            
            commande_repo.save(c);
        } else {
            throw new IllegalArgumentException("utilisateur does not exist");

        }

    }
}
