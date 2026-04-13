package tn.esprit.tpfoyer.Services;

import tn.esprit.tpfoyer.Entities.Chambre;
import java.util.List;

public interface IChambreServices {
    Chambre ajouterChambre(Chambre chambre);
    List<Chambre> afficherChambres();
    Chambre afficherChambreSelonID(long idChambre);
    Chambre modifierChambre(Chambre chambre);
    void supprimerChambre(long idChambre);

    Chambre addChambreAndReservation(Chambre chambre);
    void assignReservationToChambre(long idChambre, long idReservation);
    void unassignReservationFromChambre(long idChambre, long idReservation);
}