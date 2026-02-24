package tn.esprit.tpfoyer.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.Entities.Bloc;
import tn.esprit.tpfoyer.Services.IBlocServices;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Bloc")
public class BlocRestController {

    @Autowired
     private IBlocServices blocServices;

    // http://localhost:8089/tpfoyer/Bloc/afficherTous
    @GetMapping("/afficherTous")
    public List<Bloc> afficherTousLesBlocs() {
        return blocServices.afficherAllBlocs();
    }

    // http://localhost:8089/tpfoyer/Bloc/afficher/{id}
    @GetMapping("/afficher/{id}")
    public Bloc afficherBloc(@PathVariable("id") Long idBloc) {
        return blocServices.afficherBloc(idBloc);
    }

    // http://localhost:8089/tpfoyer/Bloc/ajouter
    @PostMapping("/ajouter")
    public Bloc ajouterBloc(@RequestBody Bloc bloc) {
        return blocServices.ajouterBloc(bloc);
    }

    // http://localhost:8089/tpfoyer/Bloc/modifier
    @PutMapping("/modifier")
    public Bloc modifierBloc(@RequestBody Bloc bloc) {
        return blocServices.modifierBloc(bloc);
    }

    // http://localhost:8089/tpfoyer/Bloc/supprimer/{id}
    @DeleteMapping("/supprimer/{id}")
    public void supprimerBloc(@PathVariable("id") Long idBloc) {
        blocServices.removeBloc(idBloc);
    }
}