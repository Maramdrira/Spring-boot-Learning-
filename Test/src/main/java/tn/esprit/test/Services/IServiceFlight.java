package tn.esprit.test.Services;

import tn.esprit.test.Entities.Flight;

import java.util.List;

public interface IServiceFlight {
  List<Flight> afficherTousLesFlight();
Flight afficherFlight(Long idFlight);
Flight ajouterFlight(Flight flight);
Flight modifierFlight(Flight flight);
void supprimerFlight(Long idFlight);
}
