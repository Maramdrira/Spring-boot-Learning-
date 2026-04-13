package tn.esprit.tpfoyer.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.Entities.Foyer;
import tn.esprit.tpfoyer.Services.IFoyerServices;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Foyer")
public class FoyerRestController {
    @Autowired
     private IFoyerServices foyerServices;

    // http://localhost:8089/tpFoyer/Foyer/afficherTous
    @GetMapping("/afficherTous")
    public List<Foyer> afficherTousLesFoyers() {
        return foyerServices.afficherTousLesFoyers();
    }

    // http://localhost:8089/tpFoyer/Foyer/afficher/{id}
    @GetMapping("/afficher/{id}")
    public Foyer afficherFoyer(@PathVariable("id") Long idFoyer) {
        return foyerServices.afficherFoyer(idFoyer);
    }

    // http://localhost:8089/tpFoyer/Foyer/ajouter
    @PostMapping("/ajouter")
    public Foyer ajouterFoyer(@RequestBody Foyer foyer) {
        return foyerServices.ajouterFoyer(foyer);
    }

    // http://localhost:8089/tpFoyer/Foyer/modifier
    @PutMapping("/modifier")
    public Foyer modifierFoyer(@RequestBody Foyer foyer) {
        return foyerServices.modifierFoyer(foyer);
    }

    // http://localhost:8089/tpFoyer/Foyer/supprimer/{id}
    @DeleteMapping("/supprimer/{id}")
    public void supprimerFoyer(@PathVariable("id") Long idFoyer) {
        foyerServices.supprimerFoyer(idFoyer);
    }

    // ==================== TP8 - EXO 1 : Affectations / Désaffectations ====================

    // http://localhost:8089/tpFoyer/Foyer/ajouter-foyer-et-bloc
    @PostMapping("/ajouter-foyer-et-bloc")
    public Foyer ajouterFoyerEtBloc(@RequestBody Foyer foyer) {
        return foyerServices.ajouterFoyerEtBloc(foyer);
    }

    // http://localhost:8089/tpFoyer/Foyer/affecter-bloc-a-foyer/{idBloc}/{idFoyer}
    @PutMapping("/affecter-bloc-a-foyer/{idBloc}/{idFoyer}")
    public void affecterBlocAFoyer(@PathVariable Long idBloc, @PathVariable Long idFoyer) {
        foyerServices.affecterBlocAFoyer(idBloc, idFoyer);
    }

    // http://localhost:8089/tpFoyer/Foyer/desaffecter-bloc-de-foyer/{idBloc}/{idFoyer}
    @PutMapping("/desaffecter-bloc-de-foyer/{idBloc}/{idFoyer}")
    public void desaffecterBlocDeFoyer(@PathVariable Long idBloc, @PathVariable Long idFoyer) {
        foyerServices.desaffecterBlocDeFoyer(idBloc, idFoyer);
    }
}