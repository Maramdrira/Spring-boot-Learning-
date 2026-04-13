package tn.esprit.tpprojet.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tpprojet.Entities.Projet;
import tn.esprit.tpprojet.Entities.ProjetDetail;

import java.util.List;

public interface ProjetDetailRepository extends JpaRepository<ProjetDetail, Long> {
   // List<Projet> findBy


}
