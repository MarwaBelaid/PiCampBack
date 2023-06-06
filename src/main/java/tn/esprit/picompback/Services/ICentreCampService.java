package tn.esprit.picompback.Services;

import tn.esprit.picompback.Entities.CentreCamp;
import tn.esprit.picompback.Repositories.CentreCampRepository;

import java.util.List;

public interface ICentreCampService {

    CentreCamp AjouterCentreCamp(CentreCamp cca) ;

    List<CentreCamp> GetCentreCamps() ;

    void updateCentreCamp (CentreCamp cca) ;

    CentreCamp GetCentreCamp(long id) ;

    void DeleteCentreCamp(long id) ;

}
