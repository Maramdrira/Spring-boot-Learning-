package tn.esprit.tpprojet.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Set;


@Entity  // Marks this class as a JPA entity (will be mapped to a database table)
@Getter  // Lombok - generates getters for all fields
@Setter  // Lombok - generates setters for all fields  
@AllArgsConstructor  // Lombok - generates constructor with all arguments
@NoArgsConstructor   // Lombok - generates empty constructor
@FieldDefaults(level = AccessLevel.PRIVATE)  // Lombok - makes all fields private by default
public class Entreprise {

    @Id  // Marks this field as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment in database
    Long idEntreprise;  // Company ID

    String nom;      // Company name
    String adresse;  // Company address

    /**
     * One-to-Many relationship with Equipe
     * One company can have multiple teams
     * mappedBy = "entreprise" means the Equipe entity owns the relationship (has the foreign key)
     */
    @OneToMany(mappedBy = "entreprise")  // mappedBy prevents duplicate foreign key column
            Set<Equipe> equipes;  // Set of teams belonging to this company (no duplicates)
}

// ======================================================================
// Database Structure:
// Table: entreprise
// Columns: id_entreprise (PK, auto-increment), nom, adresse
// 
// Relationship: One entreprise -> Many equipes
// The foreign key "entreprise_id_entreprise" will be in the equipe table
// ======================================================================