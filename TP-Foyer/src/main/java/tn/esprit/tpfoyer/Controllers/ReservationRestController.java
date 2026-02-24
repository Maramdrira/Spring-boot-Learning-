package tn.esprit.tpfoyer.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.Entities.Reservation;
import tn.esprit.tpfoyer.Services.IReservationServices;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Reservation")
public class ReservationRestController {
     @Autowired
     private IReservationServices reservationServices;

    // http://localhost:8089/tpFoyer/Reservation/afficherTous
    @GetMapping("/afficherTous")
    public List<Reservation> afficherToutesLesReservations() {
        return reservationServices.afficherToutesLesReservations();
    }

    // http://localhost:8089/tpFoyer/Reservation/afficher/{id}
    @GetMapping("/afficher/{id}")
    public Reservation afficherReservation(@PathVariable("id") Long idReservation) {
        return reservationServices.afficherReservation(idReservation);
    }

    // http://localhost:8089/tpFoyer/Reservation/ajouter
    @PostMapping("/ajouter")
    public Reservation ajouterReservation(@RequestBody Reservation reservation) {
        return reservationServices.ajouterReservation(reservation);
    }

    // http://localhost:8089/tpFoyer/Reservation/modifier
    @PutMapping("/modifier")
    public Reservation modifierReservation(@RequestBody Reservation reservation) {
        return reservationServices.modifierReservation(reservation);
    }

    // http://localhost:8089/tpFoyer/Reservation/supprimer/{id}
    @DeleteMapping("/supprimer/{id}")
    public void supprimerReservation(@PathVariable("id") Long idReservation) {
        reservationServices.supprimerReservation(idReservation);
    }
}