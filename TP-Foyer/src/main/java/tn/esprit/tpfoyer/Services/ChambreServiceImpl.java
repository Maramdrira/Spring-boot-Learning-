package tn.esprit.tpfoyer.Services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.Entities.Chambre;
import tn.esprit.tpfoyer.Entities.Reservation;
import tn.esprit.tpfoyer.Repositories.ChambreRepository;
import tn.esprit.tpfoyer.Repositories.ReservationRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ChambreServiceImpl implements IChambreServices {
    @Autowired
    private ChambreRepository chambreRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Chambre ajouterChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public List<Chambre> afficherChambres() {
        return chambreRepository.findAll();
    }

    @Override
    public Chambre afficherChambreSelonID(long idChambre) {
        return chambreRepository.findById(idChambre).get();
    }

    @Override
    public Chambre modifierChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public void supprimerChambre(long idChambre) {
        chambreRepository.deleteById(idChambre);
    }
    @Transactional  // ← ADD THIS
    @Override
    public Chambre addChambreAndReservation(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public void assignReservationToChambre(long idChambre, long idReservation) {
        Chambre chambre = chambreRepository.findById(idChambre).get();
        Reservation reservation = reservationRepository.findById(idReservation).get();

        chambre.getReservations().add(reservation);
        chambreRepository.save(chambre);
    }

    @Override
    public void unassignReservationFromChambre(long idChambre, long idReservation) {
        Chambre chambre = chambreRepository.findById(idChambre).get();
        Reservation reservation = reservationRepository.findById(idReservation).get();
        // Enlever le fils (Reservation) du parent (Chambre)
        chambre.getReservations().remove(reservation);
        chambreRepository.save(chambre);
    }


}
