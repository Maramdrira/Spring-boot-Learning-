package tn.esprit.tpprojet.Services;

import tn.esprit.tpprojet.Entities.Projet;
import java.util.List;

/**
 * SERVICE LAYER EXPLANATION:
 * --------------------------
 * What is a Service?
 * - A service layer contains business logic between controllers and repositories
 * - It handles transactions, business rules, and orchestrates data operations
 * - Separates business logic from controllers (clean architecture)
 *
 * Interface vs Class:
 * - Interface defines WHAT operations are available (contract)
 * - Implementation class defines HOW operations are performed
 * - Allows for loose coupling and easier testing (can mock the interface)
 *
 * Why use an Interface?
 * 1. Abstraction - hides implementation details
 * 2. Flexibility - can have multiple implementations
 * 3. Testability - easy to mock for unit tests
 * 4. Dependency Injection - Spring can inject different implementations
 */

public interface IProjetServices {

    /**
     * Adds a new project to the database
     * @param projet The project object to add (without ID)
     * @return The saved project with generated ID
     * Example usage: 
     *   Projet newProjet = new Projet();
     *   newProjet.setSujet("New Project");
     *   Projet saved = projetService.ajouterProjet(newProjet);
     */
    Projet ajouterProjet (Projet projet);

    /**
     * Retrieves all projects from the database
     * @return List of all projects (empty list if none found)
     * Example usage:
     *   List<Projet> allProjects = projetService.afficherProjet();
     *   for(Projet p : allProjects) { System.out.println(p.getSujet()); }
     */
    List<Projet> afficherProjet();

    /**
     * Retrieves a specific project by its ID
     * @param idProjet The ID of the project to find
     * @return The project if found, null otherwise
     * Example usage:
     *   Projet projet = projetService.afficherProjetSelonID(1L);
     *   if(projet != null) { // do something }
     */
    Projet afficherProjetSelonID(long idProjet);

    /**
     * Updates an existing project
     * @param projet The project object with updated fields (must include ID)
     * @return The updated project
     * Example usage:
     *   Projet projetToUpdate = projetService.afficherProjetSelonID(1L);
     *   projetToUpdate.setSujet("Updated Subject");
     *   Projet updated = projetService.modifierProjet(projetToUpdate);
     */
    Projet modifierProjet(Projet projet);

    /**
     * Deletes a project by its ID
     * @param idProjet The ID of the project to delete
     * Example usage:
     *   projetService.supprimerProjet(1L); // Deletes project with ID 1
     */
    void supprimerProjet (long idProjet);
}

// ======================================================================
// TYPICAL SERVICE LAYER ARCHITECTURE:
// ======================================================================
/*
 
 Controller Layer (REST API)
         ↓
 Service Interface (IProjetServices) ← You are here
         ↓
 Service Implementation (ProjetServicesImpl)
         ↓
 Repository Layer (ProjetRepository)
         ↓
 Database
 
 // ======================================================================
 // STANDARD CRUD OPERATIONS MAPPING:
 // ======================================================================
 // 
 // HTTP Method | Service Method    | Operation
 // ------------|-------------------|------------------
 // POST        | ajouterProjet     | Create
 // GET         | afficherProjet    | Read All
 // GET (/{id}) | afficherProjetSelonID | Read One
 // PUT         | modifierProjet    | Update
 // DELETE      | supprimerProjet   | Delete
 // 
 // ======================================================================
 // BENEFITS OF SERVICE LAYER:
 // ======================================================================
 // 
 // 1. Transaction Management
 //    @Transactional annotation can be added here
 // 
 // 2. Business Logic Validation
 //    if(projet.getSujet() == null) throw new Exception()
 // 
 // 3. Cross-cutting Concerns
 //    Logging, security, caching can be applied
 // 
 // 4. Multiple Repository Coordination
 //    Can combine data from multiple repositories
 // 
 // ======================================================================
*/