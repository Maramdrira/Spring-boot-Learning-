package tn.esprit.tpfoyer.Services;

import tn.esprit.tpfoyer.Entities.Reservation;
import java.util.List;

public interface IReservationServices {
    List<Reservation> afficherToutesLesReservations();
    Reservation afficherReservation(Long idReservation);
    Reservation ajouterReservation(Reservation reservation);
    Reservation modifierReservation(Reservation reservation);
    void supprimerReservation(Long idReservation);
}