package tn.esprit.picompback.Services.CampService;

import tn.esprit.picompback.Entities.Activity;

import java.util.List;

public interface IActivityService {

    Activity AjouterActivity(Activity a) ;

    List<Activity> GetActivities() ;

    void updateActivity (Activity a) ;

    Activity GetActivity(long id) ;

    void DeleteActivity(long id) ;
    String AffecterActivityAuCentreCamp(long idActivity,long idCamp) ;


}
