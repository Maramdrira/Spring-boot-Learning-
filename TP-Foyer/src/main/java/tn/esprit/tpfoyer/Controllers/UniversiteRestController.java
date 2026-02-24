package tn.esprit.tpfoyer.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.Entities.Universite;
import tn.esprit.tpfoyer.Services.IUniversiteServices;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Universite")
public class UniversiteRestController {


    @Autowired
   private   IUniversiteServices universiteServices;

    // http://localhost:8089/tpFoyer/Universite/afficherTous
    @GetMapping("/afficherTous")
    public List<Universite> afficherToutesLesUniversites() {
        return universiteServices.afficherToutesLesUniversites();
    }

    // http://localhost:8089/tpFoyer/Universite/afficher/{id}
    @GetMapping("/afficher/{id}")
    public Universite afficherUniversite(@PathVariable("id") Long idUniversite) {
        return universiteServices.afficherUniversite(idUniversite);
    }

    // http://localhost:8089/tpFoyer/Universite/ajouter
    @PostMapping("/ajouter")
    public Universite ajouterUniversite(@RequestBody Universite universite) {
        return universiteServices.ajouterUniversite(universite);
    }

    // http://localhost:8089/tpFoyer/Universite/modifier
    @PutMapping("/modifier")
    public Universite modifierUniversite(@RequestBody Universite universite) {
        return universiteServices.modifierUniversite(universite);
    }

    // http://localhost:8089/tpFoyer/Universite/supprimer/{id}
    @DeleteMapping("/supprimer/{id}")
    public void supprimerUniversite(@PathVariable("id") Long idUniversite) {
        universiteServices.supprimerUniversite(idUniversite);
    }
}