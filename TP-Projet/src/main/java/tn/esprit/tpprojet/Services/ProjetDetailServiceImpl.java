package tn.esprit.tpprojet.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.tpprojet.Entities.Projet;
import tn.esprit.tpprojet.Entities.ProjetDetail;
import tn.esprit.tpprojet.Repositories.ProjetDetailRepository;
import tn.esprit.tpprojet.Repositories.ProjetRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;



@Service
@Slf4j
@AllArgsConstructor
public class ProjetDetailServiceImpl implements IProjetDetailServices {

    // ========== DEPENDENCY INJECTION ==========
    private final ProjetDetailRepository projetDetailRepository;
    private final ProjetRepository projetRepository;

    // =====================================================================================
    // BASIC CRUD OPERATIONS
    // =====================================================================================

    @Override
    public ProjetDetail ajouterProjetDetail(ProjetDetail projetDetail) {
        log.info("Adding new project detail with description: {}", projetDetail.getDescription());
        // Save returns the saved entity with generated ID
        ProjetDetail saved = projetDetailRepository.save(projetDetail);
        log.info("Project detail added successfully with ID: {}", saved.getId());
        return saved;
    }

    @Override
    public List<ProjetDetail> afficherTousLesProjetDetails() {
        log.info("Fetching all project details");

        List<ProjetDetail> projetDetails = projetDetailRepository.findAll();

        log.info("Found {} project details", projetDetails.size());
        return projetDetails;
    }

    @Override
    public ProjetDetail afficherProjetDetailSelonID(Long id) {
        log.info("Fetching project detail with ID: {}", id);

        // findById returns Optional - using orElseThrow to handle not found
        ProjetDetail projetDetail = projetDetailRepository.findById(id).get();

        log.info("Found project detail: {}", projetDetail.getDescription());
        return projetDetail;
    }

    @Override
    public ProjetDetail modifierProjetDetail(ProjetDetail projetDetail) {
        log.info("Updating project detail with ID: {}", projetDetail.getId());

        // save with existing ID performs UPDATE
        ProjetDetail updated = projetDetailRepository.save(projetDetail);

        log.info("Project detail updated successfully");
        return updated;
    }

    @Override
    public void supprimerProjetDetail(Long id) {
        log.info("Deleting project detail with ID: {}", id);
        projetDetailRepository.deleteById(id);

        log.info("Project detail deleted successfully");
    }

    // =====================================================================================
    // FINDER METHODS
    // =====================================================================================

    @Override
    public ProjetDetail trouverParDescription(String description) {
        log.info("Finding project detail by description: {}", description);
        return projetDetailRepository.findByDescription(description);
    }

    @Override
    public List<ProjetDetail> trouverParTechnologie(String technologie) {
        log.info("Finding project details by technologie: {}", technologie);
        return projetDetailRepository.findAllByTechnologie(technologie);
    }

    @Override
    public List<ProjetDetail> trouverParCoutSuperieurA(Long cout) {
        log.info("Finding project details with cout > {}", cout);
        return projetDetailRepository.findByCoutGreaterThan(cout);
    }

    @Override
    public List<ProjetDetail> trouverParCoutEntre(Long minCout, Long maxCout) {
        log.info("Finding project details with cout between {} and {}", minCout, maxCout);
        return projetDetailRepository.findByCoutBetween(minCout, maxCout);
    }

    @Override
    public boolean existeParDescription(String description) {
        log.info("Checking if project detail exists with description: {}", description);
        return projetDetailRepository.existsByDescriptionIgnoreCase(description);
    }

    @Override
    public long compterParTechnologie(String technologie) {
        log.info("Counting project details by technologie: {}", technologie);
        return projetDetailRepository.countByCoutGreaterThan(0L); // Count all with cout > 0
        // Note: You may want to add a countByTechnologie method in repository
    }

    // =====================================================================================
    // DELETE METHODS
    // =====================================================================================

    @Override
    @Transactional
    public void supprimerParTechnologie(String technologie) {
        log.info("Deleting all project details with technologie: {}", technologie);
        projetDetailRepository.deleteByTechnologie(technologie);
        log.info("Deleted all project details with technologie: {}", technologie);
    }

    @Override
    @Transactional
    public long supprimerParCoutInferieurA(Long cout) {
        log.info("Deleting project details with cout < {}", cout);
        long deleted = projetDetailRepository.deleteByCoutLessThan(cout);
        log.info("Deleted {} project details", deleted);
        return deleted;
    }

    // =====================================================================================
    // JPQL QUERY METHODS
    // =====================================================================================

    @Override
    public ProjetDetail trouverParDescriptionEtCout(String description, Long cout) {
        log.info("Finding project detail by description: {} and cout: {}", description, cout);
        return projetDetailRepository.findByDescriptionAndCoutNamed(description, cout);
    }


    @Override
    public List<String> obtenirTechnologiesDistinctes() {
        log.info("Getting distinct technologies");
        return projetDetailRepository.findDistinctTechnologies();
    }

    // =====================================================================================
    // JOINS WITH PROJET
    // =====================================================================================

    @Override
    public List<ProjetDetail> trouverParSujetProjet(String sujet) {
        log.info("Finding project details by project subject: {}", sujet);
        return projetDetailRepository.findProjetDetailsByProjectSubject(sujet);
    }

    @Override
    public ProjetDetail trouverParIdProjet(Long projetId) {
        log.info("Finding project detail by project ID: {}", projetId);
        return projetDetailRepository.findProjetDetailByProjetId(projetId);
    }

    // =====================================================================================
    // AGGREGATION FUNCTIONS
    // =====================================================================================

    @Override
    public Double obtenirCoutMoyenParTechnologie(String technologie) {
        log.info("Getting average cout for technologie: {}", technologie);
        return projetDetailRepository.getAverageCoutByTechnologie(technologie);
    }

    @Override
    public Long obtenirCoutTotal() {
        log.info("Getting total cout of all project details");
        return projetDetailRepository.getTotalCout();
    }

    @Override
    public Long obtenirCoutMaximum() {
        log.info("Getting maximum cout");
        return projetDetailRepository.getMaxCout();
    }

    @Override
    public Long obtenirCoutMinimum() {
        log.info("Getting minimum cout");
        return projetDetailRepository.getMinCout();
    }

    @Override
    public Double obtenirCoutMoyenGlobal() {
        log.info("Getting global average cout");
        return projetDetailRepository.getAverageCoutAll();
    }

    // =====================================================================================
    // GROUP BY
    // =====================================================================================

    @Override
    public List<Object[]> compterParTechnologieGroupe() {
        log.info("Counting project details by technologie (grouped)");
        return projetDetailRepository.countProjetDetailsByTechnologie();
    }

    @Override
    public List<Object[]> obtenirCoutMoyenParTechnologieGroupe() {
        log.info("Getting average cout by technologie (grouped)");
        return projetDetailRepository.getAverageCoutByTechnologie();
    }

    // =====================================================================================
    // DATE-BASED QUERIES
    // =====================================================================================

    @Override
    public List<ProjetDetail> trouverParDateDebutApres(LocalDate date) {
        log.info("Finding project details started after: {}", date);
        return projetDetailRepository.findProjetDetailsStartedAfter(date);
    }

    @Override
    public List<ProjetDetail> trouverParDateDebutAvant(LocalDate date) {
        log.info("Finding project details started before: {}", date);
        return projetDetailRepository.findProjetDetailsStartedBefore(date);
    }

    @Override
    public List<ProjetDetail> rechercherParMotCleDescription(String keyword) {
        log.info("Searching project details by description keyword: {}", keyword);
        return projetDetailRepository.searchByDescriptionKeyword(keyword);
    }

    @Override
    public List<ProjetDetail> rechercherParMotCleTechnologie(String keyword) {
        log.info("Searching project details by technologie keyword: {}", keyword);
        return projetDetailRepository.searchByTechnologieKeyword(keyword);
    }

    @Override
    public List<ProjetDetail> trouverEntreDates(LocalDate startDate, LocalDate endDate) {
        log.info("Finding project details between {} and {}", startDate, endDate);
        return projetDetailRepository.findProjetDetailsBetweenDates(startDate, endDate);
    }

    @Override
    public List<ProjetDetail> trouverParAnnee(int year) {
        log.info("Finding project details by year: {}", year);
        return projetDetailRepository.findProjetDetailsByYear(year);
    }

    // =====================================================================================
    // UPDATE/MODIFYING OPERATIONS
    // =====================================================================================

    @Override
    @Transactional
    public int augmenterCoutPourTechnologie(Long amount, String technologie) {
        log.info("Increasing cout by {} for technologie: {}", amount, technologie);
        int updated = projetDetailRepository.increaseCoutForTechnologie(amount, technologie);
        log.info("Updated {} records", updated);
        return updated;
    }

    @Override
    @Transactional
    public int mettreAJourDescription(Long id, String newDescription) {
        log.info("Updating description for ID {} to: {}", id, newDescription);
        int updated = projetDetailRepository.updateDescriptionById(newDescription, id);
        log.info("Updated {} records", updated);
        return updated;
    }

    @Override
    @Transactional
    public int mettreAJourDateDebut(Long id, LocalDate newDate) {
        log.info("Updating start date for ID {} to: {}", id, newDate);
        int updated = projetDetailRepository.updateStartDate(newDate, id);
        log.info("Updated {} records", updated);
        return updated;
    }

    @Override
    @Transactional
    public int augmenterCoutDe10PourCentPourCoutInferieur(Long maxCout) {
        log.info("Increasing cout by 10% for projects with cout < {}", maxCout);
        int updated = projetDetailRepository.increaseCoutByTenPercentForProjectsUnder(maxCout);
        log.info("Updated {} records", updated);
        return updated;
    }

    // =====================================================================================
    // RELATIONSHIP METHODS
    // =====================================================================================

    @Override
    @Transactional
    public void assignerProjetDetailAProjet(Long projetDetailId, Long projetId) {
        log.info("Assigning project detail {} to project {}", projetDetailId, projetId);

        // Retrieve project detail
        ProjetDetail projetDetail = projetDetailRepository.findById(projetDetailId)
                .orElseThrow(() -> new RuntimeException("Project detail not found with ID: " + projetDetailId));

        // Retrieve project
        Projet projet = projetRepository.findById(projetId)
                .orElseThrow(() -> new RuntimeException("Project not found with ID: " + projetId));

        // Assign project detail to project
        projet.setProjetDetail(projetDetail);

        // Save the project (cascade will handle the association)
        projetRepository.save(projet);

        log.info("Successfully assigned project detail to project");
    }

    @Override
    public List<ProjetDetail> obtenirTousLesProjetDetailsAssignes() {
        log.info("Getting all assigned project details");
        return projetDetailRepository.findAllAssignedToProject();
    }

    @Override
    public List<ProjetDetail> obtenirTousLesProjetDetailsNonAssignes() {
        log.info("Getting all unassigned project details");
        return projetDetailRepository.findAllUnassigned();
    }

    // =====================================================================================
    // BULK OPERATIONS
    // =====================================================================================

    @Override
    @Transactional
    public int supprimerProjetDetailsAvecCoutInferieur(Long maxCout) {
        log.info("Deleting project details with cout < {}", maxCout);
        int deleted = projetDetailRepository.deleteProjetDetailsWithCoutLessThan(maxCout);
        log.info("Deleted {} project details", deleted);
        return deleted;
    }

    @Override
    @Transactional
    public int supprimerProjetDetailsAvantDate(LocalDate date) {
        log.info("Deleting project details started before: {}", date);
        int deleted = projetDetailRepository.deleteProjetDetailsStartedBefore(date);
        log.info("Deleted {} project details", deleted);
        return deleted;
    }

    @Override
    @Transactional
    public int supprimerProjetDetailsParTechnologie(String technologie) {
        log.info("Deleting all project details with technologie: {}", technologie);
        int deleted = projetDetailRepository.deleteProjetDetailsByTechnologie(technologie);
        log.info("Deleted {} project details", deleted);
        return deleted;
    }


    // =====================================================================================
    // NEW AFFECTATION METHODS (ALL COMMENTED - UNCOMMENT WHAT YOU NEED)
    // =====================================================================================

    /*
    // ========== CAS 2: Affecter ProjetDetail à un Projet existant ==========
    // Quand: ProjetDetail et Projet existent déjà dans la BD
    // Ce qu'on fait: on set le fils (ProjetDetail) dans le parent (Projet)

    @Override
    @Transactional
    public void assignProjetDetailToProjet(Long projetId, Long projetDetailId) {

        // 1. Récupérer Projet de la BD
        Projet projet = projetRepository.findById(projetId).get();

        // 2. Récupérer ProjetDetail de la BD
        ProjetDetail projetDetail = projetDetailRepository.findById(projetDetailId).get();

        // 3. Affecter le fils au parent
        projet.setProjetDetail(projetDetail);

        // 4. Sauvegarder (mise à jour)
        projetRepository.save(projet);

    }
    */


    /*
    // ========== CAS 3: Affecter Projet à une Equipe existante ==========
    // Quand: Projet et Equipe existent déjà dans la BD
    // Ce qu'on fait: on ajoute le Projet (fils) à la collection de l'Equipe (parent)

    @Override
    @Transactional
    public void assignProjetToEquipe(Long projetId, Long equipeId) {

        Projet projet = projetRepository.findById(projetId).get();

        // 2. Récupérer Equipe de la BD
        Equipe equipe = equipeRepository.findById(equipeId).get();

        // 3. Ajouter Projet à la collection de l'Equipe
        equipe.getProjets().add(projet);

        // 4. Sauvegarder l'Equipe (côté owning de ManyToMany)
        equipeRepository.save(equipe);
    }
    */

    /*
    // ========== CAS 4: Créer nouveau Projet + affecter à ProjetDetail existant ==========
    // Quand: ProjetDetail existe déjà, on crée un nouveau Projet
    // Ce qu'on fait: on set le ProjetDetail existant dans le nouveau Projet

    @Override
    @Transactional
    public Projet addProjetAndAssignToExistingProjetDetail(Projet projet, Long projetDetailId) {

        // 1. Récupérer ProjetDetail existant de la BD
        ProjetDetail projetDetail = projetDetailRepository.findById(projetDetailId).get();
        // 2. Affecter le ProjetDetail au nouveau Projet
        projet.setProjetDetail(projetDetail);

        // 3. Sauvegarder le nouveau Projet
        return projetRepository.save(projet);
    }
    */

    /*
    // ========== CAS 5: Désaffecter ProjetDetail d'un Projet ==========
    // Quand: Projet a un ProjetDetail, on veut l'enlever
    // Ce qu'on fait: on set projetDetail à null dans Projet

    @Override
    @Transactional
    public Projet desaffecterProjetDetailFromProjet(Long projetId) {

        // 1. Récupérer Projet de la BD
        Projet projet = projetRepository.findById(projetId).get();

        // 2. Setter à null (enlever l'association)
        projet.setProjetDetail(null);

        // 3. Sauvegarder
        return projetRepository.save(projet);
    }
    */

    /*
    // ========== CAS 6: Désaffecter Projet d'une Equipe ==========
    // Quand: Equipe contient un Projet, on veut l'enlever
    // Ce qu'on fait: on enlève le Projet de la collection de l'Equipe

    @Override
    @Transactional
    public void desaffecterProjetFromEquipe(Long projetId, Long equipeId) {

        // 1. Récupérer Projet de la BD
        Projet projet = projetRepository.findById(projetId).get();
        // 2. Récupérer Equipe de la BD
        Equipe equipe = equipeRepository.findById(equipeId).get();
        // 3. Enlever Projet de la collection de l'Equipe
        equipe.getProjets().remove(projet);

        // 4. Sauvegarder l'Equipe
        equipeRepository.save(equipe);

        log.info("CAS 6: Successfully removed");
    }
    */


//    CAS 2: projet.setProjetDetail(detail)     // Setter (One-to-One)
//    CAS 3: equipe.getProjets().add(projet)    // Add to collection (Many-to-Many)
//    CAS 4: nouveauProjet.setProjetDetail(detail) // Création + setter
//    CAS 5: projet.setProjetDetail(null)       // Remove setter
//    CAS 6: equipe.getProjets().remove(projet) // Remove from collection


    // =====================================================================================
    // SECTION: SCHEDULED TASKS (@Scheduled) - COMPLETE REFERENCE
    // =====================================================================================



        /*
         * WHAT IS @Scheduled?
         * REQUIREMENTS:
         * =====================================================================================
         * 1. Add @EnableScheduling to your main application class
         * 2. Add @Scheduled annotation on the method
         * 3. Method must return void (no return value)
         * 4. Method must have no parameters
         *
         * WHEN TO USE:
         * =====================================================================================
         * - Send automatic emails every morning
         * - Clean up old data every night
         * - Generate reports every hour
         * - Backup database every day at 2 AM
         * - Send notifications every minute
         * - Update cache every 30 minutes
         */

        // ========== TYPE 1: FIXED DELAY ==========
        /*
         * @Scheduled(fixedDelay = 5000)
         * =====================================================================================
         * WHAT: Waits 5 seconds AFTER the previous execution FINISHES
         *
         * USE WHEN: You need to wait between executions regardless of execution time
         */

    /*
    @Scheduled(fixedDelay = 5000)  // 5000 milliseconds = 5 seconds
    public void executeWithFixedDelay() {
        log.info("Task executed with fixed delay - runs 5 seconds after previous execution finishes");
        // Your business logic here
    }
    */

        // ========== TYPE 2: FIXED RATE ==========
        /*
         * @Scheduled(fixedRate = 5000)
         * =====================================================================================
         * WHAT: Executes every 5 seconds REGARDLESS of execution time
         * USE WHEN: You need consistent intervals between start times
         * EVERY chaque  kom 5 sec yb3th
         */

    /*
    @Scheduled(fixedRate = 5000)  // 5000 milliseconds = 5 seconds
    public void executeWithFixedRate() {
        log.info("Task executed with fixed rate - runs every 5 seconds from start time");
        // Your business logic here
    }
    */


        // ========== TYPE 3: CRON EXPRESSION (MOST POWERFUL) ==========
        /*
         * @Scheduled(cron = "0 0 9 * * MON")
         * =====================================================================================
         * WHAT: Executes at specific date/time - like a calendar alarm!
         *
         * CRON PATTERN: "* * * * * *"
         *              "second minute hour day month day-of-week"
         *
         * FORMAT: ┌───────────── second (0-59)
         *         │ ┌───────────── minute (0-59)
         *         │ │ ┌───────────── hour (0-23)
         *         │ │ │ ┌───────────── day of month (1-31)
         *         │ │ │ │ ┌───────────── month (1-12) (or JAN-DEC)
         *         │ │ │ │ │ ┌───────────── day of week (0-7) (0/7 = Sunday, MON-SUN)
         *         │ │ │ │ │ │
         *         * * * * * *
         *
         * SPECIAL CHARACTERS:
         * =====================================================================================
         * *     : Any value (every second, every minute, etc.)
         * ?     : No specific value (used for day-of-month or day-of-week)
         * -     : Range (ex: 10-15 means 10,11,12,13,14,15)
         * ,     : List (ex: 1,3,5 means 1, 3, and 5)
         * /     : Increment (ex: 0/15 means every 15 starting at 0)
         * L     : Last (ex: L for last day of month)
         * #     : Nth (ex: 2#3 means 3rd Tuesday of month)
         */



    /*
     * =====================================================================================
     * MONTHS - TEXT VALUES (JAN, FEB, etc.)
     * =====================================================================================
     *
     * ABBREVIATION | FULL MONTH   | NUMBER
     * =============|==============|=======
     * JAN          | January      | 1
     * FEB          | February     | 2
     * MAR          | March        | 3
     * APR          | April        | 4
     * MAY          | May          | 5
     * JUN          | June         | 6
     * JUL          | July         | 7
     * AUG          | August       | 8
     * SEP          | September    | 9
     * OCT          | October      | 10
     * NOV          | November     | 11
     * DEC          | December     | 12
     * =====================================================================================
     *
     */


    /*
     * =====================================================================================
     * DAY OF WEEK - TEXT VALUES (MON, TUE, etc.)
     * =====================================================================================
     *
     * ABBREVIATION | FULL DAY    | NUMBER
     * =============|=============|=======
     * SUN          | Sunday      | 0 or 7
     * MON          | Monday      | 1
     * TUE          | Tuesday     | 2
     * WED          | Wednesday   | 3
     * THU          | Thursday    | 4
     * FRI          | Friday      | 5
     * SAT          | Saturday    | 6
     * =====================================================================================
     *
     */



    /*
    // ========== COMMON CRON EXAMPLES ==========

    // Every second (for testing!)
    // @Scheduled(cron = "* * * * * *")

    // Every minute
    // @Scheduled(cron = "0 * * * * *")

    // Every 5 minutes
    // @Scheduled(cron = "0 * /5 * * * *")

                // Every hour (at minute 0)
                // @Scheduled(cron = "0 0 * * * *")

                // Every day at 9:00 AM
                // @Scheduled(cron = "0 0 9 * * *")

                // Every day at 5:30 PM
                // @Scheduled(cron = "0 30 17 * * *")

                // Every Monday at 9:00 AM
                // @Scheduled(cron = "0 0 9 * * MON")

                // Every Friday at 5:00 PM
                // @Scheduled(cron = "0 0 17 * * FRI")

                // First day of month at midnight
                // @Scheduled(cron = "0 0 0 1 * *")

                // Last day of month at 11:59 PM
                // @Scheduled(cron = "0 59 23 L * *")

                // Weekdays (Monday-Friday) at 8:00 AM
                // @Scheduled(cron = "0 0 8 * * MON-FRI")

                // Every Sunday at midnight
                // @Scheduled(cron = "0 0 0 * * SUN")

                // Every 30 seconds
                // @Scheduled(cron = "* /30 * * * * *")

                // Every 15 minutes (at 0, 15, 30, 45)
                // @Scheduled(cron = "0 * /15 * * * *")

                // At 10:00 AM and 6:00 PM every day
                // @Scheduled(cron = "0 0 10,18 * * *")

                // From 9 AM to 5 PM every hour
                // @Scheduled(cron = "0 0 9-17 * * *")

                // Every 10 minutes between 9 AM and 5 PM on weekdays
                // @Scheduled(cron = "0 * /10 9-17 * * MON-FRI")

                // Midnight of first Monday of each month
                // @Scheduled(cron = "0 0 0 ? * 2#1")



    /*
    // ========== PRACTICAL EXAMPLES FOR A REAL APP ==========



    // Clean old data every night at 2 AM
    @Scheduled(cron = "0 0 2 * * *")
    public void cleanOldData() {
        log.info("Cleaning old data from database...");
        // delete old records
        // projetRepository.deleteOldProjects();
    }

    // Send reminder emails every Monday at 9 AM
    @Scheduled(cron = "0 0 9 * * MON")
    public void sendReminderEmails() {
        log.info("Sending reminder emails...");
        // emailService.sendReminders();
    }

    // Backup database every Sunday at 1 AM
    @Scheduled(cron = "0 0 1 * * SUN")
    public void backupDatabase() {
        log.info("Starting database backup...");
        // backupService.backup();
    }

    // Check system health every 30 seconds
    @Scheduled(fixedRate = 30000)
    public void healthCheck() {
        log.info("Health check - System is running");
        // healthService.check();
    }

    // Update cache every 10 minutes
    @Scheduled(fixedRate = 600000)
    public void updateCache() {
        log.info("Updating application cache...");
        // cacheService.refresh();
    }

    // Process pending tasks - runs 5 seconds after previous finishes
    @Scheduled(fixedDelay = 5000)
    public void processPendingTasks() {
        log.info("Processing pending tasks...");
        // taskService.process();
    }

    // Start background job 30 seconds after app starts, then every hour
    @Scheduled(initialDelay = 30000, fixedRate = 3600000)
    public void startBackgroundJob() {
        log.info("Running background job...");
        // jobService.run();
    }
    */

}