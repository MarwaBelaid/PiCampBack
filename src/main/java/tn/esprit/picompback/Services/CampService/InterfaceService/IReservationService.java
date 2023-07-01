package tn.esprit.picompback.Services.CampService.InterfaceService;

import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.picompback.Entities.Reservation;
import tn.esprit.picompback.Entities.Utilisateurs;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IReservationService {

    String AjouterReservation(Reservation Res, long idUser, List<Long> ListActivity, int nbNuit) ;

    void sendConfirmationEmail(Reservation res, String SiteURL) throws MessagingException, UnsupportedEncodingException;

    List<Reservation> GetReservations() ;

    void updateReservation(Reservation Res);

    Reservation GetReservation(long id) ;
    public void DeleteReservation(long id);



}
