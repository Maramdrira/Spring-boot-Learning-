package tn.esprit.test.Controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.test.Entities.Flight;
import tn.esprit.test.Services.IServiceFlight;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Flight")
public class FlightController {

    @Autowired
    private IServiceFlight service  ;


    @GetMapping("/afficherTous")
    public List<Flight> afficherTousLesFlight() {
        return service.afficherTousLesFlight();
    }

    @GetMapping("/afficher/{id}")
    public Flight afficherFlight(@PathVariable("id") Long idFlight) {
        return service.afficherFlight(idFlight);
    }

    @PostMapping("/ajouter")
    public Flight ajouterFlight(@RequestBody Flight Flight) {
        return service.ajouterFlight(Flight);
    }

    @PutMapping("/modifier")
    public Flight modifierFlight(@RequestBody Flight Flight) {
        return service.modifierFlight(Flight);
    }

    @DeleteMapping("/supprimer/{id}")
    public void supprimerFlight(@PathVariable("id") Long idFlight) {
        service.supprimerFlight(idFlight);
    }
}
