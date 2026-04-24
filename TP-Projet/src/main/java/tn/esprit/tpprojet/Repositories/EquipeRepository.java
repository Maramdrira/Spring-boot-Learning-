package tn.esprit.tpprojet.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.tpprojet.Entities.Domaine;
import tn.esprit.tpprojet.Entities.Equipe;
import tn.esprit.tpprojet.Entities.Projet;

import java.util.List;
import java.util.Set;

public interface EquipeRepository extends JpaRepository<Equipe, Long> {
    boolean existsByNomIgnoreCase(String nom);
List<Equipe> streamByDomaineOrderByEntrepriseNomDesc(Domaine domaine);

@Query("select eq from Equipe eq join eq.projets p join p.projetDetail pd where pd.technologie= ?1 and pd.dateDebut> current date")
    Set<Equipe> AfficherEq (String technologie );

}


