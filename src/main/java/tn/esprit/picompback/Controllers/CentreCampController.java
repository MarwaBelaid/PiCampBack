package tn.esprit.picompback.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.picompback.Entities.CentreCamp;
import tn.esprit.picompback.Services.ICentreCampService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("CentreCamp")
public class CentreCampController {

    @Autowired
    ICentreCampService centreCampService ;

    @PostMapping("/add-CentreCamp")
    @ResponseBody
    public CentreCamp AjouterCentreCamp(@RequestBody CentreCamp c) {
        return  centreCampService.AjouterCentreCamp(c);

    }

    @GetMapping("/Get-All-CentreCamp")
    public List<CentreCamp> GetCentreCamps() {
        return centreCampService.GetCentreCamps();
    }

    @PutMapping("/Update-CentreCamp")
    public void updateCentreCamp(@RequestBody CentreCamp cca) {
        centreCampService.updateCentreCamp(cca) ;
    }

    @GetMapping("/Get-Only-CentreCamp/{idCentreCamp}")
    public CentreCamp GetCentreCamp(@PathVariable("idCentreCamp") long id) {
        return centreCampService.GetCentreCamp(id);
    }

    @DeleteMapping("/Remove-CentreCamp/{idCentreCamp}")
    public void DeleteCentreCamp(@PathVariable("idCentreCamp") long id) {
        centreCampService.DeleteCentreCamp(id);
    }

}
