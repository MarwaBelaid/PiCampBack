package tn.esprit.picompback.Controllers.BoutiqueController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.picompback.Entities.Equipement;
import tn.esprit.picompback.Services.BoutiqueServices.IProductService;

import java.util.List;
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService product_service;
    @GetMapping("/all")
    public List<Equipement> getAllProduits() {
        return product_service.retrieveAllProducts();
    }

    @GetMapping("/{id}")
    public Equipement getProduitById(@PathVariable Long id) {
        return product_service.retrieveProduct(id);
    }

    @PostMapping("/add/{idCentre}")
    public Equipement addProduit(@RequestBody Equipement p, @PathVariable long idCentre) {
        return product_service.addProduct(p,idCentre);
    }
}
