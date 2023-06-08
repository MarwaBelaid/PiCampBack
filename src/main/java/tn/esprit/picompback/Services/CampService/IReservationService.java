package tn.esprit.picompback.Services.CampService;

import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.picompback.Entities.Reservation;
import tn.esprit.picompback.Entities.Utilisateurs;

import java.util.List;

public interface IReservationService {

    String AjouterReservation(Reservation Res, long idUser, List<Long> ListActivity) ;

    List<Reservation> GetReservations() ;

    void updateReservation(Reservation Res);

    Reservation GetReservation(long id) ;
    public void DeleteReservation(long id);



}
