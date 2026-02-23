

package tn.esprit.tpprojet.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tpprojet.Entities.Entreprise;

/**
 * REPOSITORY EXPLANATION:
 * -----------------------
 * What is a Repository?
 * - A repository is a data access layer component that provides methods to interact with the database
 * - It abstracts the database operations, so you don't need to write SQL queries manually
 * - It's part of Spring Data JPA and implements the Repository pattern
 *
 * What is a Bean?
 * - A bean is an object managed by the Spring IoC (Inversion of Control) container
 * - Spring automatically creates a bean (implementation) of this interface at runtime
 * - You can inject this repository anywhere using @Autowired
 *
 * JpaRepository<T, ID> Explanation:
 * ---------------------------------
 * JpaRepository is an interface provided by Spring Data JPA that contains:
 * - T: The entity class this repository manages (Entreprise)
 * - ID: The type of the entity's primary key (Long)
 *
 * Why wrapper class (Long) instead of primitive (long)?
 * - Long is an object, long is a primitive
 * - JPA requires wrapper classes because:
 *   1. They can be null (important for database operations)
 *   2. They work with Generics (primitives can't be used with generics)
 *   3. They provide utility methods for conversion
 *
 * Other wrapper options:
 * - Integer for int
 * - String for String
 * - Date for dates
 */

public interface EntrpriseRepository extends JpaRepository<Entreprise, Long> {
    // No methods needed here!
    // JpaRepository already provides:
    // - save() - insert or update
    // - findById() - get by primary key
    // - findAll() - get all records
    // - delete() - delete a record
    // - count() - count records
    // - existsById() - check if exists
    // And many more!

    // You can add custom query methods here if needed
    // Example: List<Entreprise> findByNom(String nom);
}

// ======================================================================
// USAGE EXAMPLE (in Service class):
// ======================================================================
/*
 
 @Service
 public class EntrepriseService {
     
     // Spring injects the repository bean here automatically
     @Autowired
     private EntrpriseRepository entrepriseRepository;
     
     // Create/Update
     public Entreprise saveEntreprise(Entreprise entreprise) {
         return entrepriseRepository.save(entreprise);
     }
     
     // Read
     public Entreprise getEntrepriseById(Long id) {
         return entrepriseRepository.findById(id).orElse(null);
     }
     
     // Read all
     public List<Entreprise> getAllEntreprises() {
         return entrepriseRepository.findAll();
     }
     
     // Delete
     public void deleteEntreprise(Long id) {
         entrepriseRepository.deleteById(id);
     }
     
     // Check exists
     public boolean entrepriseExists(Long id) {
         return entrepriseRepository.existsById(id);
     }
     
     // Count
     public long countEntreprises() {
         return entrepriseRepository.count();
     }
 }
 
 // ======================================================================
 // COMMON JpaRepository METHODS:
 // ======================================================================
 // 
 // save(S entity)                    -> Saves/updates an entity
 // saveAll(Iterable<S> entities)     -> Saves multiple entities
 // findById(ID id)                    -> Finds by primary key
 // existsById(ID id)                   -> Checks if exists
 // findAll()                           -> Gets all records
 // findAllById(Iterable<ID> ids)       -> Finds by multiple IDs
 // count()                             -> Counts records
 // deleteById(ID id)                    -> Deletes by ID
 // delete(T entity)                     -> Deletes an entity
 // deleteAll()                          -> Deletes all records
 // 
 // ======================================================================
 // PRIMARY KEY TYPES:
 // ======================================================================
 // 
 // Entity ID Type    | JpaRepository Type | Wrapper Reason
 // ------------------|-------------------|-----------------
 // long              | Long              | Can be null
 // int               | Integer           | Works with generics
 // String            | String            | String is already an object
 // Date              | Date              | Date is already an object
 // 
 // ======================================================================
*/