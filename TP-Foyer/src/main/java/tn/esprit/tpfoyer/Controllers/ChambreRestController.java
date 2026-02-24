package tn.esprit.tpfoyer.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.Entities.Chambre;
import tn.esprit.tpfoyer.Services.IChambreServices;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Chambre")
public class ChambreRestController {

    @Autowired
    private IChambreServices chambreServices;

    // http://localhost:8089/tpFoyer/Chambre/afficherTous
    @GetMapping("/afficherTous")
    public List<Chambre> afficherTousLesChambres() {
        return chambreServices.afficherAllChambres();
    }

    // http://localhost:8089/tpFoyer/Chambre/afficher/{id}
    @GetMapping("/afficher/{id}")
    public Chambre afficherChambre(@PathVariable("id") Long idChambre) {
        return chambreServices.afficherChambre(idChambre);
    }

    // http://localhost:8089/tpFoyer/Chambre/ajouter
    @PostMapping("/ajouter")
    public Chambre ajouterChambre(@RequestBody Chambre chambre) {
        return chambreServices.ajouterChambre(chambre);
    }

    // http://localhost:8089/tpFoyer/Chambre/modifier
    @PutMapping("/modifier")
    public Chambre modifierChambre(@RequestBody Chambre chambre) {
        return chambreServices.modifierChambre(chambre);
    }

    // http://localhost:8089/tpFoyer/Chambre/supprimer/{id}
    @DeleteMapping("/supprimer/{id}")
    public void supprimerChambre(@PathVariable("id") Long idChambre) {
        chambreServices.supprimerChambre(idChambre);
    }
}