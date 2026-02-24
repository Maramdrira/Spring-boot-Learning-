package tn.esprit.tpfoyer.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.Entities.Reservation;
import tn.esprit.tpfoyer.Repositories.ReservationRepository;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements IReservationServices {

    @Autowired
     private ReservationRepository reservationRepository;

    @Override
    public List<Reservation> afficherToutesLesReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation afficherReservation(Long idReservation) {
        return reservationRepository.findById(idReservation).orElse(null);
    }

    @Override
    public Reservation ajouterReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation modifierReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void supprimerReservation(Long idReservation) {
        reservationRepository.deleteById(idReservation);
    }
}