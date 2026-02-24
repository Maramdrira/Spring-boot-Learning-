package tn.esprit.tpfoyer.Services;

import tn.esprit.tpfoyer.Entities.Foyer;
import java.util.List;

public interface IFoyerServices {
    List<Foyer> afficherTousLesFoyers();
    Foyer afficherFoyer(Long idFoyer);
    Foyer ajouterFoyer(Foyer foyer);
    Foyer modifierFoyer(Foyer foyer);
    void supprimerFoyer(Long idFoyer);
}