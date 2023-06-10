package tn.esprit.picompback.Controllers.BoutiqueController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.picompback.Entities.Commande;
import tn.esprit.picompback.Entities.Enumeration.TypeCommande;
import tn.esprit.picompback.Entities.Enumeration.TypePaiement;
import tn.esprit.picompback.Entities.Equipement;
import tn.esprit.picompback.Services.BoutiqueServices.ICommandeService;

@RestController
@RequestMapping("/commande")
public class CommandeController {

    @Autowired
    private ICommandeService commande_service;

    @PostMapping("/passerCommande/{idProduit}/{idClient}")
    public String PasserCommande(@RequestBody Commande c, @PathVariable Long idProduit,@PathVariable Long idClient, @RequestParam int qty) {
         commande_service.PasserCommande(c,idProduit,idClient,qty);
         return "commande passer";
    }

    @PutMapping("/updateCommande/{idCommande}")
    public String UpdateCommande(@PathVariable Long idCommande, @RequestParam TypeCommande typeCommande,@RequestParam TypePaiement typePaiement,@RequestParam int daysLocation) {
        commande_service.UpdateCommande(idCommande,typeCommande,typePaiement,daysLocation);
        return "commande mis a jour";
    }
}
