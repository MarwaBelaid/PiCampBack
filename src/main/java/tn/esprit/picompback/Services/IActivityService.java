package tn.esprit.picompback.Services;

import tn.esprit.picompback.Entities.Activity;

import java.util.List;

public interface IActivityService {

    Activity AjouterActivity(Activity a) ;

    List<Activity> GetActivities() ;

    void updateActivity (Activity a) ;

    Activity GetActivity(long id) ;

    void DeleteActivity(long id) ;
}
