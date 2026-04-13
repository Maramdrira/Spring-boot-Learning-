package tn.esprit.test.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.test.Entities.Airline;
import tn.esprit.test.Repositories.AirlineRepo;

import java.util.List;
@Service
@AllArgsConstructor
public class AirplaneServiceImpl implements IServiceAirline {
    private AirlineRepo airlinerepo;

    @Override
    public List<Airline> afficherTousLesAirline() {
        return airlinerepo.findAll(

        ) ;
    }

    @Override
    public Airline afficherAirline(Long idAirline) {
        return airlinerepo.findById(idAirline).orElse(null);
    }

    @Override
    public Airline ajouterAirnlie(Airline airline) {
        return airlinerepo.save(airline);
    }

    @Override
    public Airline modifierAirline(Airline airline) {
        return airlinerepo.save(airline);
    }

    @Override
    public void supprimerAirline(Long idAirline) {
airlinerepo.deleteById(idAirline);
    }
}
