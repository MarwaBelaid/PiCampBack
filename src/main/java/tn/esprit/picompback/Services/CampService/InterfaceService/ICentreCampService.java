package tn.esprit.picompback.Services.CampService.InterfaceService;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.picompback.Entities.CentreCamp;

import java.io.IOException;
import java.util.List;

public interface ICentreCampService {

    CentreCamp AjouterCentreCamp(CentreCamp cca, List<MultipartFile>  multipartFile) throws IOException;

    List<CentreCamp> GetCentreCamps() ;

    void updateCentreCamp (CentreCamp cca) ;

    CentreCamp GetCentreCamp(long id) ;

    void DeleteCentreCamp(long id) ;

}
