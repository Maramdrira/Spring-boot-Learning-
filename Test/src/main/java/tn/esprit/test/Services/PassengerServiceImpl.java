package tn.esprit.test.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.test.Entities.Passenger;
import tn.esprit.test.Repositories.PassengerRepo;

import java.util.List;
import java.util.PrimitiveIterator;
@Service
@AllArgsConstructor
public class PassengerServiceImpl implements IServicePassenger {

    private PassengerRepo passengerrepo;
    @Override
    public List<Passenger> afficherTousLesPassneger() {
        return passengerrepo.findAll() ;
    }

    @Override
    public Passenger afficherPassneger(Long idPassneger) {
        return passengerrepo.findById(idPassneger).orElse(null);
    }

    @Override
    public Passenger ajouterPassneger(Passenger passenger) {
        return passengerrepo.save(passenger);
    }

    @Override
    public Passenger modifierPassneger(Passenger passenger) {
        return passengerrepo.save(passenger);
    }

    @Override
    public void supprimerPassneger(Long idPassneger) {
passengerrepo.deleteById(idPassneger);
    }
}
