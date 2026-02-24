package tn.esprit.tpfoyer.Services;

import tn.esprit.tpfoyer.Entities.Chambre;
import java.util.List;

public interface IChambreServices {
    List<Chambre> afficherAllChambres();
    Chambre afficherChambre(Long idChambre);
    Chambre ajouterChambre(Chambre chambre);
    Chambre modifierChambre(Chambre chambre);
    void supprimerChambre(Long idChambre);
}