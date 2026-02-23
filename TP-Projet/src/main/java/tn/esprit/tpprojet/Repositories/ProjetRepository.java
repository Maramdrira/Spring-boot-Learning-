

package tn.esprit.tpprojet.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tpprojet.Entities.Projet;

@Repository  // Spring annotation indicating this is a repository bean (optional here since JpaRepository already includes it)
public interface ProjetRepository extends JpaRepository<Projet, Long> {
    // JpaRepository<Projet, Long> provides built-in methods:
    // - save(), findById(), findAll(), deleteById(), count(), existsById(), etc.
    // Long is the wrapper class for the primary key (id in Projet entity)

    // Custom query methods can be added here if needed
    // Example: List<Projet> findBySujet(String sujet);
}

// ======================================================================
// Usage in Service: @Autowired private ProjetRepository projetRepository;
// ======================================================================