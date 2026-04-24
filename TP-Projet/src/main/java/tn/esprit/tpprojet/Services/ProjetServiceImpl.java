package tn.esprit.tpprojet.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import tn.esprit.tpprojet.Entities.*;
import tn.esprit.tpprojet.Repositories.EntrpriseRepository;
import tn.esprit.tpprojet.Repositories.EquipeRepository;
import tn.esprit.tpprojet.Repositories.ProjetRepository;
import tn.esprit.tpprojet.Repositories.ProjetDetailRepository;
import java.util.List;
import java.util.Set;

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
    @Autowired
    private  ProjetDetailRepository projetDetailRepository;
    // ========== TYPE 1: Champ INJECTION ==========
    @Autowired  // Tells Spring to inject the repository here
    private ProjetRepository projetRepository;
    @Autowired
    private EntrpriseRepository entrpriseRepository;
    @Autowired
    private EquipeRepository equipeRepository;

    private MapperProjet mapperProjet;

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
    public ProjetDTO afficherProjetSelonID(long idProjet) {
        // findById() returns Optional<Projet>
        // .get() retrieves the actual Projet (throws exception if not found)
        // Better practice: .orElse(null) or .orElseThrow()
        Projet projet = projetRepository.findById(idProjet).get();
        return mapperProjet.convertToDTO(projet);
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

    @Override
    public void assignProjetDetailToProjet(long idProjet, long idProjetDetail) {
        //récuperer projet et projet detail
        Projet projet = projetRepository.findById(idProjet).get();
        ProjetDetail projetDetail = projetDetailRepository.findById(idProjetDetail).get();
        //Affecter fils ll pere (projet detail to projet)
        projet.setProjetDetail(projetDetail);
        //mettre a jour le pere (projet)
        projetRepository.save(projet);
    }

    @Override
    public Entreprise afficherSelonNom(String nom) {
        return entrpriseRepository.readByNom(nom);
    }

    @Override
    public List<Entreprise> afficherEntrepriseContenantMotTriParAdd(String nom) {
        return entrpriseRepository.streamByNomContainsOrderByAdresseAsc(nom);
    }

    @Override
    public boolean verifierEquipe(String nom) {
        return equipeRepository.existsByNomIgnoreCase(nom);
    }

    @Override
    public List<Equipe> AffEquipeContenntintUnDomainTrieeParEntNom(Domaine domain) {
        return equipeRepository.streamByDomaineOrderByEntrepriseNomDesc(domain);
    }

    @Override
    public Set<Entreprise> AfficherSelonAdresseQuery(String adresse) {
        return entrpriseRepository.afficherSelonAddresseN(adresse);
    }

    @Override
    public Set<Entreprise> AfficherSelonDomain(Domaine domaine) {
        return entrpriseRepository.afficherSelonEq(domaine);
    }

    @Override
    public Set<Projet> AfficherSelonCout(long cout , String technologie) {
        return projetRepository.afficherSelonCout(cout , technologie);
    }

    @Override
    public void ajoutProj(String sujet) {
         projetRepository.ajouerProj(sujet);
    }

    @Override
    public void ModifyProj(String sujet, long id) {
        projetRepository.updateProj(sujet , id); ;
    }

    @Override
    public void SeuppProj(long id) {
projetRepository.deleteProj(id);
    }

    @Override
    public ProjetDetailDTO GetdetaildPRojet(long idDetailP) {
      //  return projetRepository.findById(idDetailP).get();
        ProjetDetail projetDetail = projetDetailRepository.findById(idDetailP).get();
        return convertDTO(projetDetail);
    }


    //maaping manulel de proj dt too proj dtal dto
    ProjetDetailDTO convertDTO(ProjetDetail projetDetail)
    {
        ProjetDetailDTO projetDetailDTO = new ProjetDetailDTO();
        projetDetailDTO.setDescription(projetDetailDTO.getDescription());
        projetDetailDTO.setTechnologie(projetDetailDTO.getTechnologie());
        projetDetailDTO.setDeteDebut(projetDetailDTO.getDeteDebut());

        return projetDetailDTO;
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
