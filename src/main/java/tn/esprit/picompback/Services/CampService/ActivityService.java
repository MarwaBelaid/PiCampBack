package tn.esprit.picompback.Services.CampService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.picompback.Entities.Activity;
import tn.esprit.picompback.Entities.CentreCamp;
import tn.esprit.picompback.Entities.DetailsActivity;
import tn.esprit.picompback.Repositories.CampRepos.ActivityRepository;
import tn.esprit.picompback.Repositories.CampRepos.CentreCampRepository;
import tn.esprit.picompback.Repositories.CampRepos.DetailsActivityRepository;
import tn.esprit.picompback.Services.CampService.InterfaceService.IActivityService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ActivityService implements IActivityService {

    @Autowired
    ActivityRepository activityRepository ;

    @Autowired
    CentreCampRepository centreCampRepository ;
    @Autowired
    DetailsActivityRepository detailsActivityRepository ;


    @Override
    public Activity AjouterActivity(Activity a) {

        Set<DetailsActivity> da = new HashSet<>() ;
        da=a.getDetailsActivity() ;
        if(da != null) {

             for (DetailsActivity d : da) {
                d.setActivity(a);
                detailsActivityRepository.save(d);
            }

            a.setDetailsActivity(da);
            ModifierIdDetails(a);
            activityRepository.save(a);
        }
        return a;
    }
    public void ModifierIdDetails(Activity a)
    {
        int indiceMax = detailsActivityRepository.finMaxIdByActivity(a.getId_activity()) ;
        int indice= indiceMax ;
        for(DetailsActivity da : a.getDetailsActivity())
        {
            da.setIdDetailsActivity("A"+a.getId_activity()+"D"+(indiceMax-indice+1));
            indice--;
        }
    }

    @Override
    public List<Activity> GetActivities() {
        return activityRepository.findAll();
    }

    @Override
    public void updateActivity(Activity a) {
        activityRepository.save(a) ;
    }

    @Override
    public Activity GetActivity(long id) {
        return activityRepository.findById(id).orElse(null);
    }

    @Override
    public void DeleteActivity(long id) {
        activityRepository.delete(activityRepository.findById(id).get());
    }

    @Override
    public String AffecterActivityAuCentreCamp(long idActivity, long idCamp) {
        CentreCamp camp =centreCampRepository.findById(idCamp).orElse(null);
        Activity Act =activityRepository.findById(idActivity).orElse(null);
        System.out.println(camp);
        if((!centreCampRepository.findById(idCamp).isPresent()) && (!activityRepository.findById(idActivity).isPresent()))
            return  "Le Ce Centre de camp " +  idCamp+ " et L'Activity "+idActivity + " non trouvés " ;
        else if(!centreCampRepository.findById(idCamp).isPresent())
            return  "le Centre de camp " +  idCamp+ " non trouvé " ;
        else if (!activityRepository.findById(idActivity).isPresent())
            return "L'Activity " +  idActivity+ " non trouvé  " ;
        else {
            Act.setActivity_CentreCamp(camp);
            activityRepository.save(Act);
            return "Affectation avec success" ;
        }
    }
}
