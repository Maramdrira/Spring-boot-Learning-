package tn.esprit.tpprojet.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpprojet.Entities.Projet;
import tn.esprit.tpprojet.Repositories.ProjetRepository;

import java.util.List;

@Service  // Marks this class as a Spring Service bean (business logic layer)
@AllArgsConstructor  // Lombok: generates constructor with all arguments (for constructor injection)
public class ProjetServiceImpl implements IProjetServices{

    /**
     * DEPENDENCY INJECTION EXPLANATION:
     * --------------------------------
     * What is Dependency Injection?
     * - Spring creates objects (beans) and "injects" them where needed
     * - Instead of: private ProjetRepository repo = new ProjetRepository();
     * - Spring does it automatically: private ProjetRepository repo;
     *
     * 3 TYPES OF INJECTION:
     */

    // ========== TYPE 1: Champ INJECTION ==========
    @Autowired  // Tells Spring to inject the repository here
    private ProjetRepository projetRepository;

    /*
     * Champ INJECTION (current - @Autowired on field)
     * ------------------------------------------------
     * How it works: Spring uses reflection to inject directly into the field
     *
     * ✅ Advantages:
     *   - Simple and concise (less code)
     *   - Easy to read and understand
     *
     * ❌ Disadvantages:
     *   - Hard to test (can't mock easily without reflection)
     *   - Not immutable (field can be changed)
     *   - Hidden dependencies (hard to see what's needed)
     *   - Breaks encapsulation (private field modified externally)
     *
     * Best for: Simple applications, quick prototyping
     */

    // ========== TYPE 2: CONSTRUCTOR INJECTION ==========
    // private final ProjetRepository projetRepository;  // final = immutable

    /* Constructor Injection (commented out example)
    public ProjetServiceImpl(ProjetRepository pr) {
        this.projetRepository = pr;
    }
    */

    /*
     * CONSTRUCTOR INJECTION (using @AllArgsConstructor)
     * -------------------------------------------------
     * How it works: Dependencies provided through constructor parameters
     *
     * ✅ Advantages:
     *   - Immutable (final fields can't be changed)
     *   - Easy to test (pass mocks in constructor)
     *   - Clear dependencies (all required in constructor)
     *   - Null safety (prevents null dependencies)
     *   - Spring recommended approach!
     *
     * ❌ Disadvantages:
     *   - More verbose (need to write constructor)
     *   - Circular dependencies issues
     *
     * Best for: Production applications, unit testing, clean architecture
     *
     * Note: @AllArgsConstructor creates this constructor automatically!
     */

    // ========== TYPE 3: SETTER INJECTION ==========
    /* Setter Injection (commented out example)
    @Autowired
    private void setProjetRepository(ProjetRepository pr) {
        this.projetRepository = pr;
    }
    */

    /*
     * SETTER INJECTION (via @Autowired on setter)
     * -------------------------------------------
     * How it works: Spring calls the setter method to inject dependency
     *
     * ✅ Advantages:
     *   - Optional dependencies (can be set later)
     *   - Reconfigurable at runtime
     *   - Clear method name shows what's being injected
     *
     * ❌ Disadvantages:
     *   - Not immutable
     *   - More verbose than field injection
     *   - Can be called after initialization
     *
     * Best for: Optional dependencies, dependencies that might change
     */

    /*
     * COMPARISON TABLE:
     * =================================================================
     * Feature           | Field     | Constructor | Setter
     * ------------------|-----------|-------------|-----------
     * Immutability      | ❌ No     | ✅ Yes      | ❌ No
     * Testability       | ❌ Hard   | ✅ Easy     | ✅ Easy
     * Verbosity         | ✅ Low    | ❌ High     | Medium
     * Spring Recommended| ❌ No     | ✅ Yes      | Optional
     * Circular Deps     | ✅ Works  | ❌ Fails    | ✅ Works
     * Null Safety       | ❌ No     | ✅ Yes      | ❌ No
     * =================================================================
     *
     * MODERN BEST PRACTICE: Use CONSTRUCTOR INJECTION!
     * - Spring team recommends it
     * - @AllArgsConstructor does it automatically
     * - Makes testing easier
     * - Ensures immutability
     */

    @Override
    public Projet ajouterProjet(Projet projet) {
        // save() does both INSERT (new) and UPDATE (existing with ID)
        return projetRepository.save(projet);
        //saveall mt3 list w save obj

    }

    @Override
    public List<Projet> afficherProjet() {
        // findAll() returns all records from database
        return projetRepository.findAll();
    }

    @Override
    public Projet afficherProjetSelonID(long idProjet) {
        // findById() returns Optional<Projet>
        // .get() retrieves the actual Projet (throws exception if not found)
        // Better practice: .orElse(null) or .orElseThrow()
        return projetRepository.findById(idProjet).get();
        //get by id trj3 projet direct jawha behy ama bech yfs5ouha fel verion el jeya donc lawa7 nest3mo find by id mais trj3lnaa optinal<projet> ken ml9atouch trj3 .empty donc nzidoo .get() find all mtes7a9tech

    }

    @Override
    public Projet modifierProjet(Projet projet) {
        // save() with existing ID will UPDATE instead of INSERT
        return projetRepository.save(projet);
    }

    @Override
    public void supprimerProjet(long idProjet) {
        // deleteById() removes record with given ID
        projetRepository.deleteById(idProjet);
        //delte by id id fel url ama delete lezmha id fi wst obj bech y3rfhaa

    }
}

// ======================================================================
// OPTIONAL VS GET() EXPLANATION:
// ======================================================================
/*
 * findById() returns Optional<T> - a container that might or might not contain a value
 *
 * Better ways to handle Optional:
 *
 * 1. Return null if not found:
 *    return projetRepository.findById(idProjet).orElse(null);
 *
 * 2. Throw custom exception:
 *    return projetRepository.findById(idProjet)
 *           .orElseThrow(() -> new RuntimeException("Project not found"));
 *
 * 3. Provide default value:
 *    return projetRepository.findById(idProjet)
 *           .orElse(new Projet()); // empty project
 *
 * 4. Check if present:
 *    Optional<Projet> optional = projetRepository.findById(idProjet);
 *    if(optional.isPresent()) {
 *        return optional.get();
 *    }
 *    return null;
 *
 * Using .get() directly is risky - throws NoSuchElementException if not found!
 */
