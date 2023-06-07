package tn.esprit.picompback.Services.CampService;

import tn.esprit.picompback.Entities.CentreCamp;

import java.util.List;

public interface ICentreCampService {

    CentreCamp AjouterCentreCamp(CentreCamp cca) ;

    List<CentreCamp> GetCentreCamps() ;

    void updateCentreCamp (CentreCamp cca) ;

    CentreCamp GetCentreCamp(long id) ;

    void DeleteCentreCamp(long id) ;

}
