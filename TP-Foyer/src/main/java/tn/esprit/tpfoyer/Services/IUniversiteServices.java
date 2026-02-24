package tn.esprit.tpfoyer.Services;

import tn.esprit.tpfoyer.Entities.Universite;
import java.util.List;

public interface IUniversiteServices {
    List<Universite> afficherToutesLesUniversites();
    Universite afficherUniversite(Long idUniversite);
    Universite ajouterUniversite(Universite universite);
    Universite modifierUniversite(Universite universite);
    void supprimerUniversite(Long idUniversite);
}