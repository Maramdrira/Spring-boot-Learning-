package tn.esprit.tpfoyer.Services;

import tn.esprit.tpfoyer.Entities.Foyer;
import java.util.List;

public interface IFoyerServices {
    List<Foyer> afficherTousLesFoyers();
    Foyer afficherFoyer(Long idFoyer);
    Foyer ajouterFoyer(Foyer foyer);
    Foyer modifierFoyer(Foyer foyer);
    void supprimerFoyer(Long idFoyer);

    Foyer ajouterFoyerEtBloc(Foyer foyer);                    // Cas 1 adapté
    void affecterBlocAFoyer(Long idBloc, Long idFoyer);       // Cas 3 adapté
    void desaffecterBlocDeFoyer(Long idBloc, Long idFoyer);   // Cas 6 adapté
}