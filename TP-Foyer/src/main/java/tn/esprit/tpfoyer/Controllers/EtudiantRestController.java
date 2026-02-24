package tn.esprit.tpfoyer.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.Entities.Etudiant;
import tn.esprit.tpfoyer.Services.IEtudiantServices;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Etudiant")
public class EtudiantRestController {

    @Autowired
     private IEtudiantServices etudiantServices;

    // http://localhost:8089/tpFoyer/Etudiant/afficherTous
    @GetMapping("/afficherTous")
    public List<Etudiant> afficherTousLesEtudiants() {
        return etudiantServices.afficherTousLesEtudiants();
    }

    // http://localhost:8089/tpFoyer/Etudiant/afficher/{id}
    @GetMapping("/afficher/{id}")
    public Etudiant afficherEtudiant(@PathVariable("id") Long idEtudiant) {
        return etudiantServices.afficherEtudiant(idEtudiant);
    }

    // http://localhost:8089/tpFoyer/Etudiant/ajouter
    @PostMapping("/ajouter")
    public Etudiant ajouterEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantServices.ajouterEtudiant(etudiant);
    }

    // http://localhost:8089/tpFoyer/Etudiant/modifier
    @PutMapping("/modifier")
    public Etudiant modifierEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantServices.modifierEtudiant(etudiant);
    }

    // http://localhost:8089/tpFoyer/Etudiant/supprimer/{id}
    @DeleteMapping("/supprimer/{id}")
    public void supprimerEtudiant(@PathVariable("id") Long idEtudiant) {
        etudiantServices.supprimerEtudiant(idEtudiant);
    }
}