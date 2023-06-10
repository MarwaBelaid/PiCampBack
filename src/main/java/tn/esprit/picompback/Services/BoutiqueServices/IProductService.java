package tn.esprit.picompback.Services.BoutiqueServices;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.picompback.Entities.*;

import java.io.IOException;
import java.util.List;


public interface IProductService {
    List<Equipement> retrieveAllProducts();

    Equipement addProduct(Equipement p,Long idCentre, MultipartFile multipartFile) throws IOException;
    Equipement retrieveProduct(Long id);
    String getImage(Long idProduit);


}
