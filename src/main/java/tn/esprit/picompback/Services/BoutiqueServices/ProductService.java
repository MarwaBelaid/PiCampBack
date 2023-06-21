package tn.esprit.picompback.Services.BoutiqueServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.picompback.Entities.*;
import tn.esprit.picompback.Entities.Enumeration.CategorieEquipement;
import tn.esprit.picompback.Entities.Enumeration.ColorEquipement;
import tn.esprit.picompback.Entities.Enumeration.SizeEquipement;
import tn.esprit.picompback.Repositories.BoutiqueRepos.*;
import tn.esprit.picompback.Repositories.CampRepos.CentreCampRepository;
import tn.esprit.picompback.Utils.FileUploadUtil;

import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class ProductService implements IProductService {
    private static final Logger logger = LogManager.getLogger(ProductService.class);
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
    public Equipement addProduct(Equipement p, Long idCentre, MultipartFile multipartFile) throws IOException {
        CentreCamp Centre = centreCamp_repo.findById(idCentre).orElse(null);
        if (Centre !=null) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            p.setPhotos(fileName);
            p.setEquipement_CentreCamp(Centre);
            Equipement savedProduct =product_repo.save(p);
            logger.info("*************************In method " + savedProduct.getId_equipement() + " : ");
            String uploadDir = "images/equipment-photos/" + p.getId_equipement();

            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

            return savedProduct;
        } else {
            throw new IllegalArgumentException("Centre does not exist");
        }
    }

    @Override
    public String getImage(Long idProduit) {

        Equipement eq = product_repo.findById(idProduit).get();
        return eq.getPhotosImagePath();
    }

    @Override
    public List<Equipement> search(float priceMin, float priceMax, SizeEquipement size, ColorEquipement color, CategorieEquipement catg){
        logger.info("*************************In method " + priceMin + " : " + priceMax);
        return product_repo.searchProducts(priceMin,  priceMax,  size,  color,  catg);
    }

}
