package tn.esprit.tpprojet.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.tpprojet.Entities.ProjetDetail;
import tn.esprit.tpprojet.Entities.Domaine;
import tn.esprit.tpprojet.Entities.Projet;

import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

// =====================================================================================
//                    PROJET DETAIL REPOSITORY - COMPLETE REFERENCE
// =====================================================================================

@Repository
public interface ProjetDetailRepository extends JpaRepository<ProjetDetail, Long> {

    // JpaRepository provides these automatically:
    // - save(), saveAll()
    // - findById(), findAll()
    // - delete(), deleteById(), deleteAll()
    // - count(), existsById()
    // - flush(), saveAndFlush()



    // ========== BASIC FINDERS ==========
    ProjetDetail findByDescription(String description);
    List<ProjetDetail> findAllByTechnologie(String technologie);
    Set<ProjetDetail> findByTechnologie(String technologie);
    List<ProjetDetail> findAllByCout(Long cout);
    Set<ProjetDetail> findByCout(Long cout);

    // ========== WITH OPERATORS ==========
    List<ProjetDetail> findByCoutGreaterThan(Long cout);
    List<ProjetDetail> findByCoutBetween(Long minCout, Long maxCout);
    List<ProjetDetail> findByTechnologieContaining(String technologie);


    // ========== EXISTENCE AND COUNT ==========
    boolean existsByDescriptionIgnoreCase(String description);
    boolean existsByTechnologie(String technologie);
    long countByCoutGreaterThan(Long cout);

    // ========== DELETE OPERATIONS ==========
    /*
     * WHY @Transactional ON DELETE?
     * DELETE is a MODIFYING operation that changes database state.
     * Spring requires @Transactional for any operation that MODIFIES data.
     * Without it, your delete WON'T WORK or will throw an exception!
     */
    @Transactional
    void deleteByDescription(String description);
    @Transactional
    void deleteByTechnologie(String technologie);
    @Transactional
    long deleteByCoutLessThan(Long cout);




    // =====================================================================================
    // SECTION 3: JPQL QUERIES -
    // * JPQL uses: Entity names + Field names (Java class names)
    // * SQL uses:   Table names + Column names (Database names)

    // =====================================================================================

    // ========== 3 TYPES OF JPQL PARAMETERS ==========

    // TYPE 1: Named Parameter (BEST PRACTICE - use @Param)
    @Query("select pd from ProjetDetail pd where pd.description = :description and pd.cout = :cout")
    ProjetDetail findByDescriptionAndCoutNamed(@Param("description") String description, @Param("cout") Long cout);

    // TYPE 2: Positional Parameter (use ?1, ?2, etc.)
    @Query("select pd from ProjetDetail pd where pd.description = ?1 and pd.cout = ?2")
    ProjetDetail findByDescriptionAndCoutPositional(String description, Long cout);



    // ========== SELECT SPECIFIC FIELDS ==========
    @Query("select pd.description, pd.cout from ProjetDetail pd")
    List<Object[]> findDescriptionsAndCouts();  // Returns array of Objects

    @Query("select pd.description from ProjetDetail pd where pd.technologie = :technologie")
    List<String> findDescriptionsByTechnologie(@Param("technologie") String technologie);

    @Query("select pd.technologie from ProjetDetail pd group by pd.technologie")
    List<String> findDistinctTechnologies();

    // ========== JOINS ==========
    // INNER JOIN with Projet entity
    @Query("select pd from ProjetDetail pd join pd.projet p where p.sujet = :sujet")
    List<ProjetDetail> findProjetDetailsByProjectSubject(@Param("sujet") String sujet);

    @Query("select pd from ProjetDetail pd join pd.projet p where p.id = :projetId")
    ProjetDetail findProjetDetailByProjetId(@Param("projetId") Long projetId);
// =====================================================================================
// 10 HARD JPQL JOIN QUERIES - WITH EXPLANATIONS
// =====================================================================================

    // ========== QUERY 1: Simple JOIN with condition ==========
    /*
     * Trouver tous les ProjetDetails dont le Projet a un sujet contenant un mot-clé
     *
     * JOIN: ProjetDetail -> Projet
     * Condition: p.sujet LIKE %keyword%
     * Returns: List of ProjetDetail
     */
    @Query("SELECT pd FROM ProjetDetail pd JOIN pd.projet p WHERE p.sujet LIKE %:keyword%")
    List<ProjetDetail> findProjetDetailsByProjectSubjectContaining(@Param("keyword") String keyword);

    // ========== QUERY 2: JOIN with multiple conditions ==========
    /*
     * Trouver les ProjetDetails avec un cout > 1000 ET dont le Projet a un budget > 50000
     *
     * JOIN: ProjetDetail -> Projet
     * Conditions: pd.cout > :minCout AND p.budget > :minBudget
     * Returns: List of ProjetDetail
     */
    @Query("SELECT pd FROM ProjetDetail pd JOIN pd.projet p WHERE pd.cout > :minCout AND p.budget > :minBudget")
    List<ProjetDetail> findExpensiveDetailsWithExpensiveProject(
            @Param("minCout") Long minCout,
            @Param("minBudget") Double minBudget);

    // ========== QUERY 3: DOUBLE JOIN (ProjetDetail -> Projet -> Equipe) ==========
    /*
     * Trouver tous les ProjetDetails des Projets qui appartiennent à une Equipe spécifique
     *
     * JOIN 1: ProjetDetail -> Projet
     * JOIN 2: Projet -> Equipe (via Many-to-Many)
     * Condition: e.nom = :equipeNom
     * Returns: List of ProjetDetail
     */
    @Query("SELECT pd FROM ProjetDetail pd JOIN pd.projet p JOIN p.equipes e WHERE e.nom = :equipeNom")
    List<ProjetDetail> findProjetDetailsByEquipeNom(@Param("equipeNom") String equipeNom);

    // ========== QUERY 4: DOUBLE JOIN with DISTINCT ==========
    /*
     * Trouver les ProjetDetails UNIQUES des Projets qui ont plus de 2 équipes
     *
     * JOIN: ProjetDetail -> Projet -> Equipe
     * GROUP BY: pd.id
     * HAVING: COUNT(e) > 2
     * DISTINCT: Évite les doublons
     * Returns: List of distinct ProjetDetail
     */
    @Query("SELECT DISTINCT pd FROM ProjetDetail pd JOIN pd.projet p JOIN p.equipes e GROUP BY pd.id HAVING COUNT(e) > 2")
    List<ProjetDetail> findProjetDetailsWithMoreThanTwoEquipes();

    // ========== QUERY 5: JOIN with ORDER BY and LIMIT ==========
    /*
     * Trouver les 5 ProjetDetails les plus chers, avec leur Projet trié par cout décroissant
     *
     * JOIN: ProjetDetail -> Projet
     * ORDER BY: pd.cout DESC
     * Returns: List of ProjetDetail (first 5)
     */
    @Query("SELECT pd FROM ProjetDetail pd JOIN pd.projet p ORDER BY pd.cout DESC")
    List<ProjetDetail> findTop5MostExpensiveProjetDetails(Pageable pageable);
// Usage: findTop5MostExpensiveProjetDetails(PageRequest.of(0, 5))

    // ========== QUERY 6: JOIN with BETWEEN and DATE condition ==========
    /*
     * Trouver les ProjetDetails dont la date de début est entre deux dates
     * ET dont le Projet a un budget entre deux valeurs
     *
     * JOIN: ProjetDetail -> Projet
     * Conditions: pd.dateDebut BETWEEN :start AND :end
     *            p.budget BETWEEN :minBudget AND :maxBudget
     * Returns: List of ProjetDetail
     */
    @Query("SELECT pd FROM ProjetDetail pd JOIN pd.projet p WHERE pd.dateDebut BETWEEN :startDate AND :endDate AND p.budget BETWEEN :minBudget AND :maxBudget")
    List<ProjetDetail> findProjetDetailsByDateRangeAndBudgetRange(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("minBudget") Double minBudget,
            @Param("maxBudget") Double maxBudget);

    // ========== QUERY 7: JOIN with EXISTS (sous-requête) ==========
    /*
     * Trouver les ProjetDetails qui appartiennent à des Projets qui ont AU MOINS UNE Equipe
     *
     * JOIN: ProjetDetail -> Projet
     * EXISTS: Vérifie l'existence d'au moins une équipe
     * Returns: List of ProjetDetail
     */
    @Query("SELECT pd FROM ProjetDetail pd JOIN pd.projet p WHERE EXISTS (SELECT e FROM Equipe e JOIN e.projets ep WHERE ep.id = p.id)")
    List<ProjetDetail> findProjetDetailsWhereProjectHasAtLeastOneEquipe();

    // ========== QUERY 8: JOIN with SIZE function ==========
    /*
     * Trouver les ProjetDetails des Projets qui ont EXACTEMENT 2 équipes
     *
     * JOIN: ProjetDetail -> Projet
     * SIZE(p.equipes): Compte le nombre d'équipes
     * Condition: SIZE = 2
     * Returns: List of ProjetDetail
     */
    @Query("SELECT pd FROM ProjetDetail pd JOIN pd.projet p WHERE SIZE(p.equipes) = 2")
    List<ProjetDetail> findProjetDetailsWhereProjectHasExactlyTwoEquipes();

    // ========== QUERY 9: TRIPLE JOIN (ProjetDetail -> Projet -> Equipe -> Entreprise) ==========
    /*
     * Trouver les ProjetDetails des Projets dont les équipes appartiennent à une Entreprise spécifique
     *
     * JOIN 1: ProjetDetail -> Projet
     * JOIN 2: Projet -> Equipe
     * JOIN 3: Equipe -> Entreprise
     * Condition: ent.nom = :entrepriseNom
     * Returns: List of ProjetDetail
     */
    @Query("SELECT DISTINCT pd FROM ProjetDetail pd " +
            "JOIN pd.projet p " +
            "JOIN p.equipes e " +
            "JOIN e.entreprise ent " +
            "WHERE ent.nom = :entrepriseNom")
    List<ProjetDetail> findProjetDetailsByEntrepriseNom(@Param("entrepriseNom") String entrepriseNom);

    // ========== QUERY 10: JOIN with AGGREGATION and GROUP BY ==========
    /*
     * Pour chaque Projet, afficher le nombre de ProjetDetails et le cout total
     * (Projet peut avoir plusieurs ProjetDetails? Dans ton cas c'est One-to-One, mais pour l'exemple)
     *
     * JOIN: Projet -> ProjetDetail
     * GROUP BY: p.id
     * SELECT: COUNT(pd), SUM(pd.cout)
     * Returns: List of Object[] where [0]=Projet, [1]=count, [2]=totalCout
     */
    @Query("SELECT p, COUNT(pd), SUM(pd.cout) FROM Projet p LEFT JOIN p.projetDetail pd GROUP BY p.id")
    List<Object[]> getProjectStatisticsWithDetailCountAndTotalCout();

    // ========== BONUS QUERY: JOIN with FETCH (évite LazyInitializationException) ==========
    /*
     * Trouver un ProjetDetail par ID et charger son Projet immédiatement (EAGER)
     *
     * JOIN FETCH: Charge le Projet en même temps que le ProjetDetail
     * Utile pour éviter LazyInitializationException en dehors de la transaction
     */
    @Query("SELECT pd FROM ProjetDetail pd JOIN FETCH pd.projet WHERE pd.id = :id")
    Optional<ProjetDetail> findByIdWithProject(@Param("id") Long id);

    // ========== AGGREGATION FUNCTIONS ==========
    @Query("select avg(pd.cout) from ProjetDetail pd where pd.technologie = :technologie")
    Double getAverageCoutByTechnologie(@Param("technologie") String technologie);

    @Query("select sum(pd.cout) from ProjetDetail pd")
    Long getTotalCout();

    @Query("select max(pd.cout) from ProjetDetail pd")
    Long getMaxCout();

    @Query("select min(pd.cout) from ProjetDetail pd")
    Long getMinCout();

    @Query("select count(pd) from ProjetDetail pd where pd.cout > :cout")
    Long countProjetDetailsWithCoutGreaterThan(@Param("cout") Long cout);

    @Query("select avg(pd.cout) from ProjetDetail pd")
    Double getAverageCoutAll();

    // ========== GROUP BY ==========
    @Query("select pd.technologie, count(pd) from ProjetDetail pd group by pd.technologie")
    List<Object[]> countProjetDetailsByTechnologie();

    @Query("select pd.technologie, avg(pd.cout) from ProjetDetail pd group by pd.technologie")
    List<Object[]> getAverageCoutByTechnologie();

    // ========== CONDITIONS ==========
    @Query("select pd from ProjetDetail pd where pd.cout between :minCout and :maxCout")
    List<ProjetDetail> findByCoutRange(@Param("minCout") Long minCout, @Param("maxCout") Long maxCout);

    @Query("select pd from ProjetDetail pd where pd.dateDebut > :date")
    List<ProjetDetail> findProjetDetailsStartedAfter(@Param("date") LocalDate date);

    @Query("select pd from ProjetDetail pd where pd.dateDebut < :date")
    List<ProjetDetail> findProjetDetailsStartedBefore(@Param("date") LocalDate date);

    @Query("select pd from ProjetDetail pd where pd.description like %:keyword%")
    List<ProjetDetail> searchByDescriptionKeyword(@Param("keyword") String keyword);

    @Query("select pd from ProjetDetail pd where pd.technologie like %:tech%")
    List<ProjetDetail> searchByTechnologieKeyword(@Param("tech") String tech);

    @Query("select pd from ProjetDetail pd where pd.description like %:keyword% or pd.technologie like %:keyword%")
    List<ProjetDetail> searchByDescriptionOrTechnologie(@Param("keyword") String keyword);

    // ========== ORDER BY ==========
    @Query("select pd from ProjetDetail pd order by pd.cout desc")
    List<ProjetDetail> findAllSortedByCoutDesc();

    @Query("select pd from ProjetDetail pd where pd.technologie = :technologie order by pd.description asc, pd.cout desc")
    List<ProjetDetail> findByTechnologieSorted(@Param("technologie") String technologie);

    // ========== DISTINCT ==========
    @Query("select distinct pd.technologie from ProjetDetail pd")
    List<String> findDistinctTechnologiess();

    // ========== EXISTS ==========
    @Query("select case when count(pd) > 0 then true else false end from ProjetDetail pd where pd.description = :description")
    boolean isProjetDetailExists(@Param("description") String description);

    // =====================================================================================
    // SECTION 4: MODIFYING QUERIES (UPDATE/DELETE) - PROJET DETAIL VERSION
    // =====================================================================================
    /*
     * @Modifying is REQUIRED for INSERT, UPDATE, DELETE operations!
     * @Transactional is also REQUIRED!
     */

    // ========== UPDATE OPERATIONS ==========
    @Modifying
    @Transactional
    @Query("update ProjetDetail pd set pd.cout = pd.cout + :amount where pd.technologie = :technologie")
    int increaseCoutForTechnologie(@Param("amount") Long amount, @Param("technologie") String technologie);

    @Modifying
    @Transactional
    @Query("update ProjetDetail pd set pd.description = :description where pd.id = :id")
    int updateDescriptionById(@Param("description") String description, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update ProjetDetail pd set pd.technologie = :technologie, pd.cout = :cout where pd.id = :id")
    int updateProjetDetailDetails(@Param("technologie") String technologie, @Param("cout") Long cout, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update ProjetDetail pd set pd.dateDebut = :date where pd.id = :id")
    int updateStartDate(@Param("date") LocalDate date, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update ProjetDetail pd set pd.cout = pd.cout * 1.1 where pd.cout < :maxCout")
    int increaseCoutByTenPercentForProjectsUnder(@Param("maxCout") Long maxCout);

    // ========== DELETE OPERATIONS ==========
    @Modifying
    @Transactional
    @Query("delete from ProjetDetail pd where pd.technologie = :technologie")
    int deleteProjetDetailsByTechnologie(@Param("technologie") String technologie);

    @Modifying
    @Transactional
    @Query("delete from ProjetDetail pd where pd.cout < :maxCout")
    int deleteProjetDetailsWithCoutLessThan(@Param("maxCout") Long maxCout);

    @Modifying
    @Transactional
    @Query("delete from ProjetDetail pd where pd.dateDebut < :date")
    int deleteProjetDetailsStartedBefore(@Param("date") LocalDate date);


    // =====================================================================================
    // SECTION 5: QUERIES WITH PROJET RELATIONSHIP
    // =====================================================================================

    // Find ProjetDetail by Projet ID
    @Query("select pd from ProjetDetail pd where pd.projet.id = :projetId")
    Optional<ProjetDetail> findByProjetId(@Param("projetId") Long projetId);

    // Find all ProjetDetails that have a Projet assigned
    @Query("select pd from ProjetDetail pd where pd.projet is not null")
    List<ProjetDetail> findAllAssignedToProject();

    // Find all ProjetDetails with no Projet assigned
    @Query("select pd from ProjetDetail pd where pd.projet is null")
    List<ProjetDetail> findAllUnassigned();

    // =====================================================================================
    // SECTION 6: DATE-BASED QUERIES
    // =====================================================================================

    @Query("select pd from ProjetDetail pd where pd.dateDebut between :startDate and :endDate")
    List<ProjetDetail> findProjetDetailsBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("select pd from ProjetDetail pd where year(pd.dateDebut) = :year")
    List<ProjetDetail> findProjetDetailsByYear(@Param("year") int year);

    @Query("select pd from ProjetDetail pd where month(pd.dateDebut) = :month")
    List<ProjetDetail> findProjetDetailsByMonth(@Param("month") int month);





// =====================================================================================
// SECTION 10: QUICK REFERENCE CARD FOR TEST
// =====================================================================================
    /*
     * =====================================================================================
     * WHAT YOU NEED      | HOW TO WRITE IT (WITH PROJETDETAIL)
     * ===================|===============================================================
     *
     * Delete             | @Transactional void deleteByTechnologie(String technologie)
     *
     * JPQL named param   | @Query("select pd from ProjetDetail pd where pd.description = :desc")
     *                    | ProjetDetail method(@Param("desc") String description)
     *
     * JPQL positional    | @Query("select pd from ProjetDetail pd where pd.description = ?1")
     *                    | ProjetDetail method(String description)
     *
     *
     * Update query       | @Modifying @Transactional
     *                    | @Query("update ProjetDetail pd set pd.cout = :cout where pd.id = :id")
     *                    | int updateCout(@Param("cout") Long cout, @Param("id") Long id)
     *
     * Join query         | @Query("select pd from ProjetDetail pd join pd.projet p where p.sujet = :sujet")
     *
     * Aggregate function | @Query("select avg(pd.cout) from ProjetDetail pd")
     *                    | Double getAverageCout()
     *
     * Pagination         | Page<ProjetDetail> findAll(Pageable pageable)
     * =====================================================================================
     */

// =====================================================================================
// SECTION 11: TROUBLESHOOTING TIPS
// =====================================================================================
    /*
     * PROBLEM: LazyInitializationException
     * SOLUTION: Use JOIN FETCH in JPQL
     * @Query("select pd from ProjetDetail pd join fetch pd.projet where pd.id = :id")
     *
     * PROBLEM: No property found for type
     * SOLUTION: Check field name spelling in derived query!
     * "findByTechnologie" works if field is "technologie"
     *
     * PROBLEM: Modifying query doesn't update database
     * SOLUTION: Add @Modifying AND @Transactional
     *
     * PROBLEM: Native query doesn't work
     * SOLUTION: Check table/column names (use actual DB names!)
     * Table: projet_detail, Columns: id, description, technologie, cout, date_debut
     *
     * PROBLEM: LocalDate vs Date
     * SOLUTION: ProjetDetail uses LocalDate for dateDebut, use LocalDate in queries!
     *
     * PROBLEM: Projet is not loaded
     * SOLUTION: Use join fetch or set fetch type to EAGER temporarily
     */

// =====================================================================================
// END OF PROJET DETAIL REPOSITORY
// =====================================================================================
}