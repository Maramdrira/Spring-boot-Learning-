package tn.esprit.test.Controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.test.Entities.Airline;
import tn.esprit.test.Services.IServiceAirline;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Airline")
public class AilineController {


@Autowired
    private IServiceAirline serviceAirline  ;


    @GetMapping("/afficherTous")
    public List<Airline> afficherTousLesBlocs() {
        return serviceAirline.afficherTousLesAirline();
    }

    @GetMapping("/afficher/{id}")
    public Airline afficherBloc(@PathVariable("id") Long idAirline) {
        return serviceAirline.afficherAirline(idAirline);
    }

    @PostMapping("/ajouter")
    public Airline ajouterBloc(@RequestBody Airline Airline) {
        return serviceAirline.ajouterAirnlie(Airline);
    }

    @PutMapping("/modifier")
    public Airline modifierBloc(@RequestBody Airline Airline) {
        return serviceAirline.modifierAirline(Airline);
    }

    @DeleteMapping("/supprimer/{id}")
    public void supprimerBloc(@PathVariable("id") Long idAirline) {
        serviceAirline.supprimerAirline(idAirline);
    }
}
