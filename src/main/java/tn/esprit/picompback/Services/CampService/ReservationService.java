package tn.esprit.picompback.Services.CampService;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import tn.esprit.picompback.Entities.Activity;
import tn.esprit.picompback.Entities.DetailsActivity;
import tn.esprit.picompback.Entities.Enumeration.EtatActivityCentreCamp;
import tn.esprit.picompback.Entities.Reservation;
import tn.esprit.picompback.Entities.Utilisateurs;
import tn.esprit.picompback.Repositories.CampRepos.ActivityRepository;
import tn.esprit.picompback.Repositories.CampRepos.DetailsActivityRepository;
import tn.esprit.picompback.Repositories.CampRepos.ReservationRepository;
import tn.esprit.picompback.Repositories.UserRepository;
import tn.esprit.picompback.Repositories.UserRepos.UtilisateurRepository;
import tn.esprit.picompback.Services.CampService.InterfaceService.IReservationService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Slf4j
@Service
public class ReservationService implements IReservationService {

    @Autowired
    ReservationRepository reservationRepository ;

    @Autowired
    UserRepository utilisateursRepository ;

    @Autowired
    ActivityRepository activityRepository ;
    @Autowired
    DetailsActivityRepository detailsActivityRepository ;

    @Autowired
    JavaMailSender MailSender ;

    // Vérifier si les identifiants d'activtée existe dans la base de donnée
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

    // Modifier l'etat d'une vers complet
    public void UpdateEtatActivity (DetailsActivity da)
    {
        if(da.getNbPlace()==0)
            da.setEtatActivity(EtatActivityCentreCamp.Complet);
        detailsActivityRepository.save(da) ;
    }

    // Récupérer la date d'activité la plus récente du reservation
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

    // Récupérer la maximum date d'activité du reservation
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

    // Ajouter une reservation simple
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
            Res.setConfirmation(false);
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

    public void sendConfirmationEmail(Reservation res, String SiteURL) throws MessagingException, UnsupportedEncodingException {

        String subject = "Confirmer votre réservation" ;
        String senderName = "aaaaaaaa";
              //  res.getDetailsActivities().get(0).getActivity().getActivity_CentreCamp().getNom_centre();
             //   res.getDetailsActivities().stream().findFirst().get().getActivity().getActivity_CentreCamp().getNom_centre();
        String mailContent = "<p>Bonjour " + res.getReservation_utilisateur().getNom_user()+ " " +   res.getReservation_utilisateur().getPrenom_user() +", </p>";
        mailContent += "<p> Cliquer sur ce lien pour confirmer ta réservation : "  ;
        String ConfirmerUrl = SiteURL+ "/Consult?id="+ res.getId_reservation()  ;

       // mailContent += "<h3><a href=\""+ SiteURL +"\"> VERIFY</a></h3>" ;


        mailContent += "Cliquez sur le lien suivant pour confirmer votre e-mail : <a href=\"http://localhost:8082/CampProject/swagger-ui/index.html\">Confirmer</a>";

        mailContent += "<p> Merci d'avance .</p>" ;

        MimeMessage message = MailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message) ;
       // helper.setFrom(res.getDetailsActivities().stream().findFirst().get().getActivity().getActivity_CentreCamp().getEmail_centre(),senderName);
       // helper.setTo(res.getReservation_utilisateur().getEmail_user());
        helper.setFrom("marwabelaid01@gmail.com",senderName);
        helper.setTo("marwabelaid01@gmail.com");

        helper.setSubject(subject);
        helper.setText(mailContent,true);
        MailSender.send(message);
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
