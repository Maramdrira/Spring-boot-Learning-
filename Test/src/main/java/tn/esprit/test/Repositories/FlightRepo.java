package tn.esprit.test.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.test.Entities.Flight;

public interface FlightRepo extends JpaRepository<Flight, Long> {
}
