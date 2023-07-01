package tn.esprit.picompback.Controllers.CampController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.picompback.Entities.Reservation;
import tn.esprit.picompback.Services.CampService.InterfaceService.IReservationService;
import tn.esprit.picompback.Utils.Utillity;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("Reservation")
public class ReservationController {

    @Autowired
    IReservationService reservationService ;

    @PostMapping("/add-Reservation/{idUser}/{ListActivity}/{nbNuit}")
    @ResponseBody
    public void AjouterReservation(@RequestBody Reservation r, @PathVariable("idUser")  long idUser, @PathVariable("ListActivity")  List<Long> ListActivity, @PathVariable("nbNuit")  int nbNuit, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {


        reservationService.AjouterReservation(r,idUser,ListActivity,nbNuit);
       // String SiteURL = Utillity.getSiteURL(request) ;
       // reservationService.sendConfirmationEmail(r,SiteURL) ;

    }

    @GetMapping("/Get-All-Reservation")
    public List<Reservation> GetReservations() {
        return reservationService.GetReservations();
    }

    @PutMapping("/Update-Reservation")
    public void updateReservation(@RequestBody Reservation r) {
        reservationService.updateReservation(r) ;
    }

    @GetMapping("/Get-Only-Reservation/{idReservation}")
    public Reservation GetReservation(@PathVariable("idReservation") long id) {
        return reservationService.GetReservation(id);
    }

    @DeleteMapping("/Remove-CentreCamp/{iReservation}")
    public void DeleteReservation(@PathVariable("idReservation") long id) {
        reservationService.DeleteReservation(id);
    }

}
