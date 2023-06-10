package tn.esprit.picompback.Services.BoutiqueServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.picompback.Entities.*;
import tn.esprit.picompback.Repositories.BoutiqueRepos.*;
import tn.esprit.picompback.Repositories.CampRepos.CentreCampRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    ProductRepo boutique_repo;

    @Autowired
    CentreCampRepository centreCamp_repo;

    @Override
    public List<Equipement> retrieveAllProducts() {
        return boutique_repo.findAll();
    }
    @Override
    public Equipement retrieveProduct(Long id) {
        return boutique_repo.findById(id).get();
    }

    @Override
    public Equipement addProduct(Equipement p, Long idCentre) {
        Optional<CentreCamp> optionalCentre = centreCamp_repo.findById(idCentre);
        if (optionalCentre.isPresent()) {
            CentreCamp centre = optionalCentre.get();
            p.setEquipement_CentreCamp(centre);
            return boutique_repo.save(p);
        } else {
            throw new IllegalArgumentException("Centre does not exist");
        }
    }


}
