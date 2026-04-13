package tn.esprit.tpprojet.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tpprojet.Entities.Domaine;
import tn.esprit.tpprojet.Entities.Equipe;
import tn.esprit.tpprojet.Entities.Projet;

import java.util.List;
import java.util.Set;

public interface EquipeRepository extends JpaRepository<Equipe, Long> {
    boolean existsByNomIgnoreCase(String nom);
List<Equipe> streamByDomaineOrderByEntrepriseNomDesc(Domaine domaine);

}


