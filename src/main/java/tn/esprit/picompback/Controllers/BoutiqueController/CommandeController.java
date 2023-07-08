package tn.esprit.picompback.Controllers.BoutiqueController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.picompback.Entities.Commande;
import tn.esprit.picompback.Entities.CommandeEquipement;
import tn.esprit.picompback.Entities.Enumeration.*;
import tn.esprit.picompback.Entities.Equipement;
import tn.esprit.picompback.Entities.Utilisateurs;
import tn.esprit.picompback.Services.BoutiqueServices.ICommandeService;

import java.util.List;

@RestController
@RequestMapping("/commande")
public class CommandeController {

    @Autowired
    private ICommandeService commande_service;

    @PostMapping("/passerCommande/{idProduit}/{idClient}")
    public String PasserCommande(@PathVariable Long idProduit,@PathVariable Long idClient, @RequestParam(value = "qty", required = false) Integer qty) {
         commande_service.PasserCommande(idProduit,idClient,qty);
         return "commande passer";
    }

    @PutMapping("/updateCommande/{idCommande}")
    public String UpdateCommande(@PathVariable Long idCommande, @RequestBody TypeCommande typeCommande,@RequestBody TypePaiement typePaiement,@RequestBody Integer daysLocation) {
        commande_service.UpdateCommande(idCommande,typeCommande,typePaiement,daysLocation);
        return "commande mis a jour";
    }

    @GetMapping("/EquipementCommandebyid/{idCommande}")
    public List<CommandeEquipement> retrieveCommandesEquipement(@PathVariable Long idCommande) {
        return commande_service.retrieveCommandesEquipement(idCommande);
    }

    @GetMapping("/CommandebyidUser/{idUser}")
    public Commande retrieveCommandeUser(@PathVariable Long idUser) {
        return commande_service.retrieveCommandeUser(idUser,EtatCommande.EnCoursTraitement);
    }


    @PutMapping("/updateCart/{idCommande}/{idEquiCommande}/{qty}")
    public String updateCart(@PathVariable Long idCommande, @PathVariable Long idEquiCommande,@PathVariable int qty) {
        commande_service.updateCart(idCommande,idEquiCommande,qty);
        return "cart mis a jour";
    }

    @GetMapping("/getUser/{idUser}")
    public Utilisateurs retrieveUser(@PathVariable Long idUser) {
        return commande_service.retrieveUser(idUser);
    }
}
