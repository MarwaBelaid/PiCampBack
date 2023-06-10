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
    ProductRepo product_repo;

    @Autowired
    CentreCampRepository centreCamp_repo;

    @Override
    public List<Equipement> retrieveAllProducts() {
        return product_repo.findAll();
    }
    @Override
    public Equipement retrieveProduct(Long id) {
        return product_repo.findById(id).get();
    }

    @Override
    public Equipement addProduct(Equipement p, Long idCentre) {
        CentreCamp Centre = centreCamp_repo.findById(idCentre).orElse(null);
        if (Centre !=null) {
            p.setEquipement_CentreCamp(Centre);
            return product_repo.save(p);
        } else {
            throw new IllegalArgumentException("Centre does not exist");
        }
    }


}
