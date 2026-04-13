package tn.esprit.test.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.test.Entities.Airline;

public interface AirlineRepo extends JpaRepository<Airline, Long> {
}
