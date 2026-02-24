package tn.esprit.tpfoyer.Services;

import tn.esprit.tpfoyer.Entities.Bloc;

import java.util.List;

public interface IBlocServices {
    List<Bloc> afficherAllBlocs();
    Bloc afficherBloc(Long idBloc);
    Bloc ajouterBloc(Bloc bloc);
    Bloc modifierBloc(Bloc bloc);
    void removeBloc(Long idBloc);
}
