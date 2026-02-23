package tn.esprit.tpprojet.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tpprojet.Entities.Equipe;

public interface EquipeRepository extends JpaRepository<Equipe, Long> {
}
