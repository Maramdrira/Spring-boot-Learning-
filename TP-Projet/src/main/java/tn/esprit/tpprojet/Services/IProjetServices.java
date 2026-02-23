package tn.esprit.tpprojet.Services;

import tn.esprit.tpprojet.Entities.Projet;

import java.util.List;

public interface IProjetServices {
    Projet ajouterProjet (Projet projet);

    List<Projet> afficherProjet();

    Projet afficherProjetSelonID(long idProjet);

    Projet modifierProjet(Projet projet);

    void supprimerProjet ( long idProjet);
}
