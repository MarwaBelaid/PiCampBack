package tn.esprit.picompback.Services.CampService.InterfaceService;

import tn.esprit.picompback.Entities.Activity;
import tn.esprit.picompback.Entities.DetailsActivity;

import java.util.List;
import java.util.Set;

public interface IActivityService {

    Activity AjouterActivity(Activity a) ;

    List<Activity> GetActivities() ;

    Activity updateActivity (Activity a) ;

    Activity GetActivity(long id) ;

    void DeleteActivity(long id) ;
    String AffecterActivityAuCentreCamp(long idActivity,long idCamp) ;


}
