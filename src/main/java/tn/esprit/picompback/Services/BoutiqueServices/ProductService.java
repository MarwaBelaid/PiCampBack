package tn.esprit.picompback.Services.BoutiqueServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.picompback.Entities.*;
import tn.esprit.picompback.Repositories.BoutiqueRepos.*;
import tn.esprit.picompback.Repositories.CampRepos.CentreCampRepository;
import tn.esprit.picompback.Utils.FileUploadUtil;

import java.io.IOException;
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
    public Equipement addProduct(Equipement p, Long idCentre, MultipartFile multipartFile) throws IOException {
        CentreCamp Centre = centreCamp_repo.findById(idCentre).orElse(null);
        if (Centre !=null) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            p.setPhotos(fileName);
            p.setEquipement_CentreCamp(Centre);
            String uploadDir = "equipment-photos/" + p.getId_equipement();

            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

            return product_repo.save(p);
        } else {
            throw new IllegalArgumentException("Centre does not exist");
        }
    }


}
