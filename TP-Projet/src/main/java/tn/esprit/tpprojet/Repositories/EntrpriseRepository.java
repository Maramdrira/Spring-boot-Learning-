package tn.esprit.tpprojet.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tpprojet.Entities.Entreprise;

public interface EntrpriseRepository extends JpaRepository<Entreprise, Long> {
}
