package tn.esprit.picompback.Controllers.CampController;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.picompback.Entities.CentreCamp;
import tn.esprit.picompback.Entities.Image;
import tn.esprit.picompback.Services.CampService.InterfaceService.ICentreCampService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("CentreCamp")
@Validated
public class CentreCampController {

    @Autowired
    ICentreCampService centreCampService ;

    private final String API_KEY = "AIzaSyCkUepSD-8gqp0oe08ftoAOUi0oYtLYeD0";

    public Set<Image> uploadImage( MultipartFile[] multipartFiles) throws IOException
    {
        Set<Image> img = new HashSet<>() ;

        for (MultipartFile multipartFile : multipartFiles) {
            Image image = new Image (
                    multipartFile.getOriginalFilename(),
                    multipartFile.getContentType(),
                    multipartFile.getBytes()
            ) ;
            img.add(image) ;

        }
        return img ;
    }


    @PostMapping(value = "/add-CentreCamp", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CentreCamp ajouterCentreCamp(CentreCamp cca, @RequestPart("image") MultipartFile[] multipartFiles) throws IOException {
      System.out.println(cca);
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        try{
            Set<Image> img = uploadImage(multipartFiles) ;
            cca.setPhotos(img);
            return centreCampService.AjouterCentreCamp(cca);

        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null ;
        }
    }

        @GetMapping("/Get-All-CentreCamp")
    public List<CentreCamp> GetCentreCamps() {
        return centreCampService.GetCentreCamps();
    }


   /* @PutMapping("/Update-CentreCamp")
    public void updateCentreCamp(@RequestBody CentreCamp cca) {
        centreCampService.updateCentreCamp(cca) ;
    }*/

   /*@PutMapping(value = "/Update-CentreCamp", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
   public CentreCamp updateCentreCamp(CentreCamp cca,
                                      @RequestPart("photos") MultipartFile[] multipartFiles) throws IOException {
       System.out.println(cca);

       cca.setPhotos(new HashSet<>());
       System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        try{
            Set<Image> img = uploadImage(multipartFiles) ;
            cca.setPhotos(img);
            return centreCampService.updateCentreCamp(cca);

        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null ;
        }
    }*/

    @PutMapping(value = "/Update-CentreCamp", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CentreCamp> updateCentreCamp(
            @ModelAttribute CentreCamp centreCamp,
            @RequestPart(name = "image", required = false) MultipartFile[] multipartFiles
    ) {
        try {
            if (multipartFiles != null && multipartFiles.length > 0) {
                Set<Image> images = uploadImage(multipartFiles);
                centreCamp.setPhotos(images);
            }

            CentreCamp updatedCentreCamp = centreCampService.updateCentreCamp(centreCamp);
            return ResponseEntity.ok(updatedCentreCamp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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
