package tn.esprit.tpfoyer.Services;

import tn.esprit.tpfoyer.Entities.Bloc;

import java.util.List;

public interface IBlocServices {
    Bloc ajouterBloc(Bloc bloc);
    List<Bloc> afficherBlocs();
    Bloc afficherBlocSelonID(long idBloc);
    Bloc modifierBloc(Bloc bloc);
    void supprimerBloc(long idBloc);

    Bloc addBlocAndFoyer(Bloc bloc);                          // Exo1 - Cas 1
    void assignBlocToFoyer(long idBloc, long idFoyer);       // Exo1 - Cas 2
    void unassignBlocFromFoyer(long idBloc);

}
