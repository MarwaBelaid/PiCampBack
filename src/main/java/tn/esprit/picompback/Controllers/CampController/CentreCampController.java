package tn.esprit.picompback.Controllers.CampController;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.picompback.Entities.CentreCamp;
import tn.esprit.picompback.Services.CampService.InterfaceService.ICentreCampService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("CentreCamp")
@Validated
public class CentreCampController {

    @Autowired
    ICentreCampService centreCampService ;

    private final String API_KEY = "AIzaSyCkUepSD-8gqp0oe08ftoAOUi0oYtLYeD0";

    @PostMapping(value = "/add-CentreCamp", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CentreCamp ajouterCentreCamp(CentreCamp cca,@RequestPart("image") List<MultipartFile> multipartFiles) throws IOException {
        return centreCampService.AjouterCentreCamp(cca, multipartFiles);
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
