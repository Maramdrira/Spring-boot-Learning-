package tn.esprit.test.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.test.Entities.Flight;
import tn.esprit.test.Repositories.FlightRepo;

import java.util.List;
@Service
@AllArgsConstructor
public class FlightServiceImpl implements IServiceFlight {
    private FlightRepo flightRepo;
    @Override
    public List<Flight> afficherTousLesFlight() {
        return flightRepo.findAll();
    }

    @Override
    public Flight afficherFlight(Long idFlight) {
        return flightRepo.findById(idFlight).orElse(null);
    }

    @Override
    public Flight ajouterFlight(Flight flight) {
        return flightRepo.save(flight);
    }

    @Override
    public Flight modifierFlight(Flight flight) {
        return flightRepo.save(flight);
    }

    @Override
    public void supprimerFlight(Long idFlight) {
flightRepo.deleteById(idFlight);
    }
}
