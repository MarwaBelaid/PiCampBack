package tn.esprit.picompback.Services.CampService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.picompback.Entities.Activity;
import tn.esprit.picompback.Repositories.CampRepos.ActivityRepository;

import java.util.List;

@Service
public class ActivityService implements IActivityService{

    @Autowired
    ActivityRepository activityRepository ;
    @Override
    public Activity AjouterActivity(Activity a) {
        return activityRepository.save(a);
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
        return activityRepository.findById(id).get();
    }

    @Override
    public void DeleteActivity(long id) {
        activityRepository.delete(activityRepository.findById(id).get());
    }
}
