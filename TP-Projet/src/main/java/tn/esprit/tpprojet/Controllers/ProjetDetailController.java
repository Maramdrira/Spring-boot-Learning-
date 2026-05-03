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

    @GetMapping("/by-project/{projetId}")
    ProjetDetail findByProjectId(@PathVariable("projetId") Long projetId) {
        return projetDetailServices.trouverParIdProjet(projetId);
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

    @GetMapping("/count-by-technologie")
    List<Object[]> countByTechnologie() {
        return projetDetailServices.compterParTechnologieGroupe();
    }

    @GetMapping("/avg-cout-by-technologie")
    List<Object[]> getAverageCoutByTechnologieGrouped() {
        return projetDetailServices.obtenirCoutMoyenParTechnologieGroupe();
    }

    // =====================================================================================
    // DATE-BASED QUERIES
    // =====================================================================================

    @GetMapping("/date/after")
    List<ProjetDetail> findByDateDebutApres(@RequestParam("date") LocalDate date) {
        return projetDetailServices.trouverParDateDebutApres(date);
    }

    @GetMapping("/date/before")
    List<ProjetDetail> findByDateDebutAvant(@RequestParam("date") LocalDate date) {
        return projetDetailServices.trouverParDateDebutAvant(date);
    }

    @GetMapping("/search/description")
    List<ProjetDetail> searchByDescriptionKeyword(@RequestParam("keyword") String keyword) {
        return projetDetailServices.rechercherParMotCleDescription(keyword);
    }

    @GetMapping("/search/technologie")
    List<ProjetDetail> searchByTechnologieKeyword(@RequestParam("keyword") String keyword) {
        return projetDetailServices.rechercherParMotCleTechnologie(keyword);
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

    @PutMapping("/{id}/date")
    int updateStartDate(@PathVariable("id") Long id, @RequestParam("date") LocalDate newDate) {
        return projetDetailServices.mettreAJourDateDebut(id, newDate);
    }

    @PutMapping("/increase-cout/by-10-percent")
    int increaseCoutByTenPercentForProjectsUnder(@RequestParam("maxCout") Long maxCout) {
        return projetDetailServices.augmenterCoutDe10PourCentPourCoutInferieur(maxCout);
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

    @GetMapping("/assigned")
    List<ProjetDetail> getAssignedProjetDetails() {
        return projetDetailServices.obtenirTousLesProjetDetailsAssignes();
    }

    @GetMapping("/unassigned")
    List<ProjetDetail> getUnassignedProjetDetails() {
        return projetDetailServices.obtenirTousLesProjetDetailsNonAssignes();
    }

    // =====================================================================================
    // BULK DELETE OPERATIONS
    // =====================================================================================

    @DeleteMapping("/bulk/cout-less-than/{maxCout}")
    int deleteByCoutLessThanBulk(@PathVariable("maxCout") Long maxCout) {
        return projetDetailServices.supprimerProjetDetailsAvecCoutInferieur(maxCout);
    }

    @DeleteMapping("/bulk/before-date")
    int deleteByDateBefore(@RequestParam("date") LocalDate date) {
        return projetDetailServices.supprimerProjetDetailsAvantDate(date);
    }

    @DeleteMapping("/bulk/technologie/{technologie}")
    int deleteByTechnologieBulk(@PathVariable("technologie") String technologie) {
        return projetDetailServices.supprimerProjetDetailsParTechnologie(technologie);
    }

    // =====================================================================================
    // AFFECTATION METHODS (CAS 1-6 from your PDF - COMMENTED)
    // =====================================================================================

    /*
    // ========== CAS 1: Ajouter Projet et ProjetDetail en même temps ==========
    // Déjà fait dans ProjetController avec cascade!
    // URL: POST /Projet/addProjet
    // Body: {"sujet":"...", "projetDetail":{...}}
    */

    /*
    // ========== CAS 2: Affecter ProjetDetail à Projet existant ==========
    // URL: PUT /projet-detail/affecter/{projetDetailId}/to-projet/{projetId}
    @PutMapping("/affecter/{projetDetailId}/to-projet/{projetId}")
    void affecterProjetDetailToProjet(
            @PathVariable("projetDetailId") Long projetDetailId,
            @PathVariable("projetId") Long projetId) {
        projetDetailServices.assignerProjetDetailAProjet(projetDetailId, projetId);
    }
    */

    /*
    // ========== CAS 3: Affecter Projet à Equipe ==========
    // Fait dans ProjetController
    */

    /*
    // ========== CAS 4: Créer Projet + affecter à ProjetDetail existant ==========
    // Fait dans ProjetController
    */

    /*
    // ========== CAS 5: Désaffecter ProjetDetail de Projet ==========
    // Ajouter dans ProjetController
    */

    /*
    // ========== CAS 6: Désaffecter Projet de Equipe ==========
    // Ajouter dans ProjetController
    */
}