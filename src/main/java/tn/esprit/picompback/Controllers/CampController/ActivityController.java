package tn.esprit.picompback.Controllers.CampController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.picompback.Entities.*;
import tn.esprit.picompback.Services.CampService.InterfaceService.IActivityService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;


import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    IActivityService activityService;

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
/*
    @PostMapping(value = "/add-Activity", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Activity AjouterActivity(
            @ModelAttribute Activity activity,
            @RequestParam("details") List<DetailsActivity> details,
            @RequestParam("image") MultipartFile[] multipartFiles
    ) throws IOException {
        try {
            Set<Image> img = uploadImage(multipartFiles);
            activity.setPhotos(img);
            System.out.println("daaaaaa");

           /* System.out.println(da);

            Set<DetailsActivity> daa = new HashSet<>(da);*/
           // a.setDetailsActivity(daa);
        /*    System.out.println("a.getDetailsActivity()");
            System.out.println(activity.getDetailsActivity());
            return activityService.AjouterActivity(activity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }*/
/*
    @PostMapping(value = "/add-Activity", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Activity ajouterActivity(
            @ModelAttribute Activity activity,
            @RequestParam("details[]") List<DetailsActivity> detailsList,
            @RequestParam("photosFile") MultipartFile[] multipartFiles
    ) throws JsonProcessingException {


        //String output = detailsJson.replaceAll("\\[object Object\\],", "");
       // ObjectMapper objectMapper = new ObjectMapper();
       // DetailsActivity[] detailsActivities = objectMapper.readValue(output, DetailsActivity[].class);

        // Assuming you want to assign the first DetailsActivity object to the activity
      //  if (detailsActivities.length > 0) {
        //    activity.setDetailsActivity(detailsActivities[0]);
       // }

        System.out.println("gttttttttttttttt");
        System.out.println(detailsList);



           // String output = detailsJson.replaceAll("\\[object Object\\],", "");
        //String json = "[{heure_debut: 16:06,heure_fin: 19:02,date: 2023-07-20,nbPlace: 10 }]";
       // String json = "[{'heure_debut': '16:06','heure_fin': '19:02','date': '2023-07-20','nbPlace': 10 }]";
      //  Type listType = new TypeToken<List<DetailsActivity>>() {}.getType();
     //   List<DetailsActivity> detailsList = new Gson().fromJson(json, listType);

       // Type listType = new TypeToken<List<DetailsActivity>>() {}.getType();
       // List<DetailsActivity> detailsList = new Gson().fromJson(json, listType);

        System.out.println("eeeeeeeeeeeeeeeeeeee");
      //  System.out.println(detailsList);

            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa");
           // DetailsActivity d =detailsList.get(0);
          //  System.out.println(detailsList);
        try {
            Set<Image> img = uploadImage(multipartFiles);
            activity.setPhotos(img);
            return activityService.AjouterActivity(activity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }


        }
*/
/*
    @PostMapping(value = "/add-Activity")
    public Activity ajouterActivity (
            Activity activity,
            DetailsActivity[] detailsActivities

    ) throws IOException {


            try {
               // Set<Image> img = uploadImage(photosFiles);
                //activity.setPhotos(img);
              return activity;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }

    }*/

    @PostMapping(value = "/add-Activity", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Activity addActivity(
            Activity activity,
            @RequestParam("listJson") String listJson,
            @RequestPart("photosFile") MultipartFile[] multipartFiles) throws IOException {

        System.out.println(listJson);
        // Convertir la chaîne JSON en objet Activity
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        // Convertir la chaîne JSON en tableau d'objets DetailsActivity
        DetailsActivity[] detailsActivities = objectMapper.readValue(listJson, DetailsActivity[].class);



          System.out.println(Arrays.toString(detailsActivities));

        try {
            Set<Image> img = uploadImage(multipartFiles);
            activity.setPhotos(img);
            Set<DetailsActivity> detailsActivitySet = new HashSet<>(Arrays.asList(detailsActivities));
            activity.setDetailsActivity(detailsActivitySet);
            return activityService.AjouterActivity(activity);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }


    @GetMapping("/Get-All-Activities")
    public List<Activity> GetActivities() {
        return activityService.GetActivities();
    }

    /*@PutMapping("/Update-Activity")
    public void updateActivity(@RequestBody Activity a) {
        activityService.updateActivity(a) ;
    }*/

    @PutMapping(value = "/Update-Activity", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Activity updateActivity (
            @ModelAttribute Activity activity,
            @RequestParam("listJson") String listJson,
            @RequestPart(name = "image", required = false) MultipartFile[] multipartFiles
    ) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        // Convertir la chaîne JSON en tableau d'objets DetailsActivity
        DetailsActivity[] detailsActivities = objectMapper.readValue(listJson, DetailsActivity[].class);
        Set<DetailsActivity> detailsActivitySet = new HashSet<>(Arrays.asList(detailsActivities));

        System.out.println(detailsActivitySet);

        try {
            if (multipartFiles != null && multipartFiles.length > 0) {
                Set<Image> images = uploadImage(multipartFiles);
                activity.setPhotos(images);
            }
            activity.setDetailsActivity(detailsActivitySet);
            System.out.println(activity);

            return activityService.updateActivity(activity);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/GetOnlyActivity/{idActivity}")
    public Activity GetActivity(@PathVariable("idActivity") long id) {
        return activityService.GetActivity(id);
    }

    @DeleteMapping("/Remove-Activity/{idActivity}")
    public void DeleteActivity(@PathVariable("idActivity") long id) {
        activityService.DeleteActivity(id);
    }

    @PutMapping("/AffecterActivityToCentreCamp/{idActivity}/{idCamp}")
    public String AffecterActivityAuCentreCamp(@PathVariable("idActivity") long idActivity, @PathVariable("idCamp")  long idCamp)
    {
        return activityService.AffecterActivityAuCentreCamp(idActivity,idCamp);
    }

}
