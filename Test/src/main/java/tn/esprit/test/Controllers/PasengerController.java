package tn.esprit.test.Controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.test.Entities.Passenger;
import tn.esprit.test.Services.IServicePassenger;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Passeneger")
public class PasengerController {
    @Autowired
    private IServicePassenger service  ;


    @GetMapping("/afficherTous")
    public List<Passenger> afficherTousLesPassenger() {
        return service.afficherTousLesPassneger();
    }

    @GetMapping("/afficher/{id}")
    public Passenger afficherPassenger(@PathVariable("id") Long idPassenger) {
        return service.afficherPassneger(idPassenger);
    }

    @PostMapping("/ajouter")
    public Passenger ajouterPassenger(@RequestBody Passenger Passenger) {
        return service.ajouterPassneger(Passenger);
    }

    @PutMapping("/modifier")
    public Passenger modifierPassenger(@RequestBody Passenger Passenger) {
        return service.modifierPassneger(Passenger);
    }

    @DeleteMapping("/supprimer/{id}")
    public void supprimerPassenger(@PathVariable("id") Long idPassenger) {
        service.supprimerPassneger(idPassenger);
    }
}
