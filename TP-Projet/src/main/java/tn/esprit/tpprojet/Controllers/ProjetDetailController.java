package tn.esprit.tpprojet.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpprojet.Entities.ProjetDetail;
import tn.esprit.tpprojet.Services.IProjetDetailServices;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/projet-detail")
public class ProjetDetailController {

    private IProjetDetailServices projetDetailServices;

    // =====================================================================================
    // BASIC CRUD
    // =====================================================================================

    @PostMapping("/add")
    ProjetDetail addProjetDetail(@RequestBody ProjetDetail projetDetail) {
        return projetDetailServices.ajouterProjetDetail(projetDetail);
    }

    @GetMapping("/all")
    List<ProjetDetail> getAllProjetDetails() {
        return projetDetailServices.afficherTousLesProjetDetails();
    }

    @GetMapping("/{id}")
    ProjetDetail getProjetDetailById(@PathVariable("id") Long id) {
        return projetDetailServices.afficherProjetDetailSelonID(id);
    }

    @PutMapping("/update")
    ProjetDetail updateProjetDetail(@RequestBody ProjetDetail projetDetail) {
        return projetDetailServices.modifierProjetDetail(projetDetail);
    }

    @DeleteMapping("/{id}")
    void deleteProjetDetail(@PathVariable("id") Long id) {
        projetDetailServices.supprimerProjetDetail(id);
    }

    // =====================================================================================
    // FINDER METHODS
    // =====================================================================================

    @GetMapping("/description/{description}")
    ProjetDetail findByDescription(@PathVariable("description") String description) {
        return projetDetailServices.trouverParDescription(description);
    }

    @GetMapping("/technologie/{technologie}")
    List<ProjetDetail> findByTechnologie(@PathVariable("technologie") String technologie) {
        return projetDetailServices.trouverParTechnologie(technologie);
    }

    @GetMapping("/cout/greater-than/{cout}")
    List<ProjetDetail> findByCoutGreaterThan(@PathVariable("cout") Long cout) {
        return projetDetailServices.trouverParCoutSuperieurA(cout);
    }

    @GetMapping("/cout/between")
    List<ProjetDetail> findByCoutBetween(
            @RequestParam("min") Long minCout,
            @RequestParam("max") Long maxCout) {
        return projetDetailServices.trouverParCoutEntre(minCout, maxCout);
    }

    @GetMapping("/exists/description/{description}")
    boolean existsByDescription(@PathVariable("description") String description) {
        return projetDetailServices.existeParDescription(description);
    }

    // =====================================================================================
    // DELETE METHODS
    // =====================================================================================

    @DeleteMapping("/technologie/{technologie}")
    void deleteByTechnologie(@PathVariable("technologie") String technologie) {
        projetDetailServices.supprimerParTechnologie(technologie);
    }

    @DeleteMapping("/cout/less-than/{cout}")
    long deleteByCoutLessThan(@PathVariable("cout") Long cout) {
        return projetDetailServices.supprimerParCoutInferieurA(cout);
    }

    // =====================================================================================
    // JPQL QUERIES
    // =====================================================================================

    @GetMapping("/search")
    ProjetDetail findByDescriptionAndCout(
            @RequestParam("description") String description,
            @RequestParam("cout") Long cout) {
        return projetDetailServices.trouverParDescriptionEtCout(description, cout);
    }

    @GetMapping("/distinct-technologies")
    List<String> getDistinctTechnologies() {
        return projetDetailServices.obtenirTechnologiesDistinctes();
    }

    // =====================================================================================
    // JOINS WITH PROJET
    // =====================================================================================

    @GetMapping("/project-subject/{sujet}")
    List<ProjetDetail> findByProjectSubject(@PathVariable("sujet") String sujet) {
        return projetDetailServices.trouverParSujetProjet(sujet);
    }



    // =====================================================================================
    // AGGREGATION FUNCTIONS
    // =====================================================================================

    @GetMapping("/avg-cout/technologie/{technologie}")
    Double getAverageCoutByTechnologie(@PathVariable("technologie") String technologie) {
        return projetDetailServices.obtenirCoutMoyenParTechnologie(technologie);
    }

    @GetMapping("/total-cout")
    Long getTotalCout() {
        return projetDetailServices.obtenirCoutTotal();
    }

    @GetMapping("/max-cout")
    Long getMaxCout() {
        return projetDetailServices.obtenirCoutMaximum();
    }

    @GetMapping("/min-cout")
    Long getMinCout() {
        return projetDetailServices.obtenirCoutMinimum();
    }

    @GetMapping("/avg-cout")
    Double getAverageCoutGlobal() {
        return projetDetailServices.obtenirCoutMoyenGlobal();
    }

    // =====================================================================================
    // GROUP BY
    // =====================================================================================



    @GetMapping("/avg-cout-by-technologie")
    List<Object[]> getAverageCoutByTechnologieGrouped() {
        return projetDetailServices.obtenirCoutMoyenParTechnologieGroupe();
    }



    @GetMapping("/date/between")
    List<ProjetDetail> findBetweenDates(
            @RequestParam("start") LocalDate startDate,
            @RequestParam("end") LocalDate endDate) {
        return projetDetailServices.trouverEntreDates(startDate, endDate);
    }

    @GetMapping("/year/{year}")
    List<ProjetDetail> findByYear(@PathVariable("year") int year) {
        return projetDetailServices.trouverParAnnee(year);
    }

    // =====================================================================================
    // UPDATE/MODIFYING OPERATIONS
    // =====================================================================================

    @PutMapping("/increase-cout/technologie/{technologie}")
    int increaseCoutForTechnologie(
            @PathVariable("technologie") String technologie,
            @RequestParam("amount") Long amount) {
        return projetDetailServices.augmenterCoutPourTechnologie(amount, technologie);
    }

    @PutMapping("/{id}/description")
    int updateDescription(@PathVariable("id") Long id, @RequestParam("description") String newDescription) {
        return projetDetailServices.mettreAJourDescription(id, newDescription);
    }



    // =====================================================================================
    // RELATIONSHIP METHODS
    // =====================================================================================

    @PutMapping("/assign/{projetDetailId}/to-project/{projetId}")
    void assignProjetDetailToProjet(
            @PathVariable("projetDetailId") Long projetDetailId,
            @PathVariable("projetId") Long projetId) {
        projetDetailServices.assignerProjetDetailAProjet(projetDetailId, projetId);
    }

    // =====================================================================================
    // BULK DELETE OPERATIONS
    // =====================================================================================

    @DeleteMapping("/bulk/cout-less-than/{maxCout}")
    int deleteByCoutLessThanBulk(@PathVariable("maxCout") Long maxCout) {
        return projetDetailServices.supprimerProjetDetailsAvecCoutInferieur(maxCout);
    }


// =====================================================================================
// AFFECTATION & DESAFFECTATION METHODS (CAS 1-6)
// =====================================================================================

    /*
     * ========== CAS 1: Ajouter Projet et ProjetDetail en même temps ==========
     * Déjà fait! CascadeType.ALL in Projet entity handles this automatically.
     * URL: POST /Projet/addProjet
     * Body: {"sujet":"...", "projetDetail":{"description":"...", "technologie":"...", "cout":..., "dateDebut":"..."}}
     */

    // ========== CAS 2: Affecter ProjetDetail existant à Projet existant ==========
    /*
     * URL: PUT http://localhost:8089/tpProjet/Projet/affecter-projet-detail/1/1
     * Body: none
     * Description: Assign existing ProjetDetail to existing Projet
     *
    @PutMapping("/affecter-projet-detail/{projetId}/{projetDetailId}")
    public void affecterProjetDetailToProjet(
            @PathVariable("projetId") Long projetId,
            @PathVariable("projetDetailId") Long projetDetailId) {
        projetServices.assignProjetDetailToProjet(projetId, projetDetailId);
    }*/

    // ========== CAS 3: Affecter Projet existant à Equipe existante ==========
    /*
     * URL: PUT http://localhost:8089/tpProjet/Projet/affecter-projet-equipe/1/1
     * Body: none
     * Description: Assign existing Projet to existing Equipe
     *
    @PutMapping("/affecter-projet-equipe/{projetId}/{equipeId}")
    public void affecterProjetToEquipe(
            @PathVariable("projetId") Long projetId,
            @PathVariable("equipeId") Long equipeId) {
        projetServices.assignProjetToEquipe(projetId, equipeId);
    }*/

    // ========== CAS 4: Créer nouveau Projet + affecter à ProjetDetail existant ==========
    /*
     * URL: POST http://localhost:8089/tpProjet/Projet/creer-projet-et-affecter/1
     * Body: {"sujet": "Nouveau projet"}
     * Description: Create new Projet and assign to existing ProjetDetail
     * Note: Do NOT include projetDetail in body!

    @PostMapping("/creer-projet-et-affecter/{projetDetailId}")
    public Projet creerProjetEtAffecterProjetDetail(
            @RequestBody Projet projet,
            @PathVariable("projetDetailId") Long projetDetailId) {
        return projetServices.addProjetAndAssignToExistingProjetDetail(projet, projetDetailId);
    }*/

    // ========== CAS 5: Désaffecter ProjetDetail de Projet ==========
    /*
     * URL: PUT http://localhost:8089/tpProjet/Projet/desaffecter-projet-detail/1
     * Body: none
     * Description: Remove ProjetDetail from Projet (set to null)

    @PutMapping("/desaffecter-projet-detail/{projetId}")
    public Projet desaffecterProjetDetailFromProjet(@PathVariable("projetId") Long projetId) {
        return projetServices.desaffecterProjetDetailFromProjet(projetId);
    }*/

    // ========== CAS 6: Désaffecter Projet de Equipe ==========
    /*
     * URL: PUT http://localhost:8089/tpProjet/Projet/desaffecter-projet-equipe/1/1
     * Body: none
     * Description: Remove Projet from Equipe

    @PutMapping("/desaffecter-projet-equipe/{projetId}/{equipeId}")
    public void desaffecterProjetFromEquipe(
            @PathVariable("projetId") Long projetId,
            @PathVariable("equipeId") Long equipeId) {
        projetServices.desaffecterProjetFromEquipe(projetId, equipeId);
    }*/
}