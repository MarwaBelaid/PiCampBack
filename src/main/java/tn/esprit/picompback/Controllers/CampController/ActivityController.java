package tn.esprit.picompback.Controllers.CampController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.picompback.Entities.Activity;
import tn.esprit.picompback.Services.CampService.IActivityService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    IActivityService activityService;

    @PostMapping("/add-Activity")
    @ResponseBody
    public Activity AjouterActivity(@RequestBody Activity a) {
        return activityService.AjouterActivity(a);
    }

    @GetMapping("/Get-All-Activities")
    public List<Activity> GetActivities() {
        return activityService.GetActivities();
    }

    @PutMapping("/Update-Activity")
    public void updateActivity(@RequestBody Activity a) {
        activityService.updateActivity(a) ;
    }

    @GetMapping("/Get-Only-Activity/{idActivity}")
    public Activity GetActivity(@PathVariable("idActivity") long id) {
        return activityService.GetActivity(id);
    }

    @DeleteMapping("/Remove-Activity/{idActivity}")
    public void DeleteActivity(@PathVariable("idActivity") long id) {
        activityService.DeleteActivity(id);
    }
}
