package tn.esprit.test.Services;

import tn.esprit.test.Entities.Passenger;

import java.util.List;

public interface IServicePassenger {

    List<Passenger> afficherTousLesPassneger();
    Passenger afficherPassneger(Long idPassneger);
    Passenger ajouterPassneger(Passenger passenger);
    Passenger modifierPassneger(Passenger passenger);
    void supprimerPassneger(Long idPassneger);
}
