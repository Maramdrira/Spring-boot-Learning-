package tn.esprit.tpprojet.Services;

import tn.esprit.tpprojet.Entities.ProjetDetail;
import tn.esprit.tpprojet.Entities.Projet;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * SERVICE INTERFACE EXPLANATION:
 * =====================================================================================
 * WHAT IS A SERVICE INTERFACE?
 * - Defines WHAT operations are available (the contract)
 * - Implementation class defines HOW operations are performed
 * - Allows for loose coupling and easier testing
 *
 * =====================================================================================
 */

public interface IProjetDetailServices {

    // ========== BASIC CRUD OPERATIONS ==========
    ProjetDetail ajouterProjetDetail(ProjetDetail projetDetail);
    List<ProjetDetail> afficherTousLesProjetDetails();
    ProjetDetail afficherProjetDetailSelonID(Long id);
    ProjetDetail modifierProjetDetail(ProjetDetail projetDetail);
    void supprimerProjetDetail(Long id);

    // ========== FINDER METHODS ==========
    ProjetDetail trouverParDescription(String description);
    List<ProjetDetail> trouverParTechnologie(String technologie);
    List<ProjetDetail> trouverParCoutSuperieurA(Long cout);
    List<ProjetDetail> trouverParCoutEntre(Long minCout, Long maxCout);
    boolean existeParDescription(String description);

    // ========== DELETE METHODS ==========
    void supprimerParTechnologie(String technologie);
    long supprimerParCoutInferieurA(Long cout);

    // ========== JPQL QUERY METHODS ==========
    ProjetDetail trouverParDescriptionEtCout(String description, Long cout);
    List<String> obtenirTechnologiesDistinctes();

    // ========== JOINS WITH PROJET ==========
    List<ProjetDetail> trouverParSujetProjet(String sujet);

    // ========== AGGREGATION FUNCTIONS ==========
    Double obtenirCoutMoyenParTechnologie(String technologie);
    Long obtenirCoutTotal();
    Long obtenirCoutMaximum();
    Long obtenirCoutMinimum();
    Double obtenirCoutMoyenGlobal();

    // ========== GROUP BY ==========

    /**
     * Counts project details by technologie
     * @return List of Object arrays [technologie, count]
     */
    List<Object[]> obtenirCoutMoyenParTechnologieGroupe();

    // ========== DATE-BASED QUERIES ==========


    List<ProjetDetail> trouverEntreDates(LocalDate startDate, LocalDate endDate);
    List<ProjetDetail> trouverParAnnee(int year);

    // ========== UPDATE/MODIFYING OPERATIONS ==========
    int augmenterCoutPourTechnologie(Long amount, String technologie);
    int mettreAJourDescription(Long id, String newDescription);

    // ========== RELATIONSHIP METHODS ==========
    void assignerProjetDetailAProjet(Long projetDetailId, Long projetId);

    // ========== BULK OPERATIONS ==========
    int supprimerProjetDetailsAvecCoutInferieur(Long maxCout);


    // ========== NEW AFFECTATION METHODS (UNCOMMENT WHAT YOU NEED) ==========

    /*
    // CAS 2: Affecter ProjetDetail existant à Projet existant
    void assignProjetDetailToProjet(Long projetId, Long projetDetailId);

    // CAS 3: Affecter Projet existant à Equipe existante
    void assignProjetToEquipe(Long projetId, Long equipeId);

    // CAS 4: Créer nouveau Projet + affecter à ProjetDetail existant
    Projet addProjetAndAssignToExistingProjetDetail(Projet projet, Long projetDetailId);

    // CAS 5: Désaffecter ProjetDetail d'un Projet
    Projet desaffecterProjetDetailFromProjet(Long projetId);

    // CAS 6: Désaffecter Projet d'une Equipe
    void desaffecterProjetFromEquipe(Long projetId, Long equipeId);
    */
}