package tn.esprit.tpprojet.Controllers;

import lombok.AllArgsConstructor;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpprojet.Entities.Projet;
import tn.esprit.tpprojet.Services.IProjetServices;
import tn.esprit.tpprojet.Services.ProjetServiceImpl;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping ("/Projet")
public class ProjetRestController {
    //api
    @Autowired
     private IProjetServices projetServices;

    @PostMapping("/addProjet") // annotation mvc relier ll web mel starter
    Projet addProjet(@RequestBody Projet projet){ // @reqbody t5lik tzid obj fel body mt3 el requette
        return projetServices.ajouterProjet(projet);
    }

    @GetMapping("/getProjet")
    List<Projet> getProjet(){
        return projetServices.afficherProjet();
    }

    @GetMapping("/getProjetById/{idP}")
    Projet getProjetById(@PathVariable("idP") long idProjet){
        return projetServices.afficherProjetSelonID(idProjet);

    }

    @PutMapping ("/updateProjet")
    Projet updateProjet(@RequestBody Projet projet){
        return projetServices.modifierProjet(projet);
    }

    @DeleteMapping("/deleteProjet/{idP}")
    void deleteProjet(@PathVariable("idP") long idProjet )
    {
        projetServices.supprimerProjet(idProjet);
    }


}

