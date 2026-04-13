package tn.esprit.test.Services;

import tn.esprit.test.Entities.Airline;

import java.util.List;

public interface IServiceAirline  {
    List<Airline> afficherTousLesAirline();
    Airline afficherAirline(Long idAirline);
    Airline ajouterAirnlie(Airline airline);
    Airline modifierAirline(Airline airline);
    void supprimerAirline(Long idAirline);
}
