package tn.esprit.picompback.Services.BoutiqueServices;

import tn.esprit.picompback.Entities.*;

import java.util.List;


public interface IProductService {
    List<Equipement> retrieveAllProducts();

    Equipement addProduct(Equipement p,Long idCentre);
    Equipement retrieveProduct(Long id);

    //void assignProductToCommande(Long idProduit, Long idStock);
}
