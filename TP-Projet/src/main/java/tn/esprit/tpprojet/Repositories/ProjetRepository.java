package tn.esprit.tpprojet.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tpprojet.Entities.Projet;

@Repository
public interface ProjetRepository extends JpaRepository<Projet,Long> {
}
