package tn.esprit.picompback.Services.CampService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import tn.esprit.picompback.Entities.Activity;
import tn.esprit.picompback.Entities.DetailsActivity;
import tn.esprit.picompback.Entities.Enumeration.Statut;
import tn.esprit.picompback.Entities.Reservation;
import tn.esprit.picompback.Entities.Utilisateurs;
import tn.esprit.picompback.Repositories.CampRepos.ActivityRepository;
import tn.esprit.picompback.Repositories.CampRepos.DetailsActivityRepository;
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
    @Autowired
    DetailsActivityRepository detailsActivityRepository ;

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

    public Date MinDateAct (Set<DetailsActivity> ListDa)
    {
        Date minDate = null ;
        for(DetailsActivity da :ListDa )
        {
            minDate = da.getDate() ;
            if(da.getDate().compareTo(minDate) <= 0 )
                minDate = da.getDate() ;
        }

        return minDate ;
    }

    public Date MaxDateAct (Set<DetailsActivity> ListDa)
    {
        Date maxDate = null ;
        for(DetailsActivity da :ListDa )
        {
            maxDate = da.getDate() ;
            if(da.getDate().compareTo(maxDate) >= 0 )
                maxDate = da.getDate() ;
        }

        return maxDate ;
    }
    public boolean AddRes (Reservation Res, Utilisateurs user, List<Long> ListActivity, int nbNuit)
    {
        boolean add = false ;
        Set<DetailsActivity> ListDa = new HashSet<>() ;
        Set<Reservation> listRes = new HashSet<>() ;
        float MontantTotal = 0;
        if(nbNuit>0)
            MontantTotal = nbNuit*(detailsActivityRepository.findById(ListActivity.get(0)).get().getActivity().getActivity_CentreCamp().getTarif_nuitee());
        for(Long IdDa : ListActivity) {
            DetailsActivity da = detailsActivityRepository.findById(IdDa).orElse(null);
            if (da != null) {
                if ((da.getNbPlace() + Res.getNbr_personne()) <= da.getActivity().getCapacite_max()) {
                    da.setNbPlace(da.getNbPlace() + Res.getNbr_personne());
                    ListDa.add(da);
                }
                listRes = da.getReservations() ;
                MontantTotal = MontantTotal + (da.getActivity().getPrix() * Res.getNbr_personne());

            Res.setReservation_utilisateur(user);
            Res.setMontant_reservation(MontantTotal);
            Res.setDate_debut(MinDateAct(ListDa));
            Res.setDate_fin(MaxDateAct(ListDa));
            listRes.add(Res) ;
            reservationRepository.save(Res);
            da.setReservations(listRes);
            detailsActivityRepository.save(da) ;
            add = true ;
        }
            else
                System.out.println(IdDa +" n'existe pas");
        }
      return  add ;
    }
    @Override
    public String AjouterReservation(Reservation Res,long idUser, List<Long> ListActivity,int nbNuit) {
        Utilisateurs user = utilisateursRepository.findById(idUser).orElse(null);

        if (user == null) {
            return "Utilisateur " + idUser + " non trouvé : ";
        }
        else if (reservationRepository.findReservationsByUtilisateur(idUser) == 0) {
                if (AddRes(Res, user, ListActivity, nbNuit) == true)
                    return "Ajout";
        }
        return "verif";
    }
       /*
        Boolean YesAdd = false ;
        //float
        Set<DetailsActivity> ListDetailAct = new HashSet<>();
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

                    for(Long iddetAct : ListActivity)
                    {   DetailsActivity detailAct = detailsActivityRepository.findById(iddetAct).get() ;
                        detailAct.setNbPlace(detailAct.getNbPlace()-Res.getNbr_personne());
                        detailsActivityRepository.save(detailAct) ;
                        ListDetailAct.add(detailAct) ;
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
            Res.setActivities(ListDetailAct);
            Res.setStatut(Statut.Déposée);
            reservationRepository.save(Res);
            return new String("Ajout de la reservation avec succes") ;
        }
        else
        return new String("echec d'ajout") ;*/


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
