

package tn.esprit.tpprojet.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.tpprojet.Entities.Projet;

import java.util.Set;

@Repository  // Spring annotation indicating this is a repository bean (optional here since JpaRepository already includes it)
public interface ProjetRepository extends JpaRepository<Projet, Long> {



    @Query("select p from Projet p join p.projetDetail pd where pd.cout > ?1 and pd.technologie= ?2")
    Set<Projet> afficherSelonCout(long cout , String technologie);


    @Modifying
    @Query(value = "insert into table-Proj (sujet) values (:sujetProj)" , nativeQuery = true)
    void ajouerProj(@Param("sujetProj") String sujet);

    @Modifying
    @Query("update Projet p set p.sujet= ?1 where p.id= ?2")
    void updateProj(String sujet , long id);

    @Modifying
    @Query("delete from Projet p where p.id= ?1")
    void deleteProj(long id);

    Long id(Long id);


    // JpaRepository<Projet, Long> provides built-in methods:
    // - save(), findById(), findAll(), deleteById(), count(), existsById(), etc.
    // Long is the wrapper class for the primary key (id in Projet entity)

    // Custom query methods can be added here if needed
    // Example: List<Projet> findBySujet(String sujet);
}

// ======================================================================
// Usage in Service: @Autowired private ProjetRepository projetRepository;
// ======================================================================