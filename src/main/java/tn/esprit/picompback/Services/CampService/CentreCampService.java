package tn.esprit.picompback.Services.CampService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.picompback.Entities.CentreCamp;
import tn.esprit.picompback.Entities.Equipement;
import tn.esprit.picompback.Repositories.CampRepos.CentreCampRepository;
import tn.esprit.picompback.Services.CampService.InterfaceService.ICentreCampService;
import tn.esprit.picompback.Utils.FileUploadUtil;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class CentreCampService implements ICentreCampService {

    @Autowired
    CentreCampRepository centreCampRepository ;

    public String SupprimerPointVirgule (String ch)
    {
        StringBuilder sb = new StringBuilder(ch);
        if (ch.endsWith(";")) {
            sb.deleteCharAt(ch.length() - 1);
            ch = sb.toString();
        }
        return ch ;
    }
    @Override
    public CentreCamp AjouterCentreCamp(CentreCamp cca, List <MultipartFile> multipartFiles) throws IOException
     {
    String Photo ="";
        for (MultipartFile multipartFile : multipartFiles) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            Photo += fileName+";"  ;

            log.info("tesssssst");
            String uploadDir = "images/camp-photos/" + cca.getId_centre();
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
       }
        cca.setPhotos_centre( SupprimerPointVirgule (Photo));
        return centreCampRepository.save(cca);
    }



    @Override
    public List<CentreCamp> GetCentreCamps() {
        return centreCampRepository.findAll();
    }

    @Override
    public void updateCentreCamp(CentreCamp cca) {
        centreCampRepository.save(cca) ;
    }

    @Override
    public CentreCamp GetCentreCamp(long id) {
        return centreCampRepository.findById(id).get();
    }

    @Override
    public void DeleteCentreCamp(long id) {
        centreCampRepository.delete(centreCampRepository.findById(id).get());
    }
}
