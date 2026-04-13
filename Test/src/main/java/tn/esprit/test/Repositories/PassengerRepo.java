package tn.esprit.test.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.test.Entities.Passenger;

public interface PassengerRepo extends JpaRepository<Passenger, Long> {
}
