package tn.esprit.picompback.Controllers.BoutiqueController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.picompback.Entities.Equipement;
import tn.esprit.picompback.Services.BoutiqueServices.IProductService;

import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping("/product")
public class ProductController {
 public String HeaderValue = "multipart/form-data";
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

    @PostMapping(value = "/add/{idCentre}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Equipement addProduit(@RequestHeader("Content-Type") String HeaderValue,@ModelAttribute Equipement p, @PathVariable long idCentre,@RequestParam("image") MultipartFile multipartFile) throws IOException {
        return product_service.addProduct(p,idCentre,multipartFile);
    }
}
