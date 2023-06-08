package tn.esprit.picompback.Services.CampService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import tn.esprit.picompback.Entities.Activity;
import tn.esprit.picompback.Entities.Enumeration.Statut;
import tn.esprit.picompback.Entities.Reservation;
import tn.esprit.picompback.Entities.Utilisateurs;
import tn.esprit.picompback.Repositories.CampRepos.ActivityRepository;
import tn.esprit.picompback.Repositories.CampRepos.ReservationRepository;
import tn.esprit.picompback.Repositories.UserRepos.UtilisateurRepository;

import java.util.*;

@Service
public class ReservationService implements IReservationService{

    @Autowired
    ReservationRepository reservationRepository ;

    @Autowired
    UtilisateurRepository utilisateursRepository ;

    @Autowired
    ActivityRepository activityRepository ;

    public Set<Activity> VerifierAvtivities (Set<Activity> ListActivity, List<Long> idActivity)
    {
        Set<Activity> list = new HashSet<>() ;
        for(Long idActivityNew  :  idActivity)
        {
            for(Activity ListActivityNew  :  ListActivity)
        {
            if(ListActivityNew.getId_activity() != idActivityNew)
                list.add(activityRepository.findById(idActivityNew).get()) ;
        }

        }
        return list ;
    }
    @Override
    public String AjouterReservation(Reservation Res,long idUser, List<Long> ListActivity) {
        Utilisateurs user = utilisateursRepository.findById(idUser).get() ;
        Boolean YesAdd = false ;
        Set<Activity> ListAct = new HashSet<>();
        Set<Activity> NewListAct = new HashSet<>();
        if(user == null)
        {
            throw new IllegalArgumentException("Utilisateur " +  user+ "non trouvé : " );
        }
        else {
            for(Reservation reservation : reservationRepository.findReservationByReservation_utilisateurAndActitvity(idUser, Res.getDate_debut(), Res.getDate_fin() ))
            {
                 if(reservation.getStatut().equals(Statut.Refusée))
                {
                   // Ajout
                    for(Long idAct : ListActivity)
                    {
                        ListAct.add(activityRepository.findById(idAct).get()) ;
                    }
                    YesAdd = true ;
                }
                else
                {   //Alert
                    throw new IllegalArgumentException("Vous avez une autre reservation au meme date " );
                }
            }
        }
        if(YesAdd == true) {
            Res.setReservation_utilisateur(user);
            Res.setActivities(ListAct);
            reservationRepository.save(Res);
            return new String("Ajout de la reservation avec succes") ;
        }
        return new String("echec d'ajout") ;
    }

    @Override
    public List<Reservation> GetReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public void updateReservation(Reservation Res) {
        reservationRepository.save(Res) ;
    }

    @Override
    public Reservation GetReservation(long id) {
        return reservationRepository.findById(id).get();
    }

    @Override
    public void DeleteReservation(long id) {
        reservationRepository.delete(reservationRepository.findById(id).get());
    }
}
