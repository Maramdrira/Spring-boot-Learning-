package tn.esprit.tpprojet.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity  // Marks this class as a JPA entity mapped to database table
@Getter  // Lombok: generates getters for all fields
@Setter  // Lombok: generates setters for all fields
@NoArgsConstructor  // Lombok: generates empty constructor
@AllArgsConstructor  // Lombok: generates constructor with all arguments
//@RequiredArgsConstructor  // Lombok: generates constructor for final/required fields (commented out)
@ToString  // Lombok: generates toString() method
@EqualsAndHashCode  // Lombok: generates equals() and hashCode() methods
@FieldDefaults(level = AccessLevel.PRIVATE)  // Lombok: makes all fields private by default
@Table(name = "table-Proj")  // Specifies custom table name in database (instead of default "projet")
public class Projet {

    @Id  // Marks this field as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment in database
    Long id;

    String sujet;  // Project subject/title

    @OneToOne(cascade = CascadeType.ALL)  // One-to-One relationship with ProjetDetail.
    // Cascade ALL means any operation on Projet affects its ProjetDetail.
    // Other cascade options: PERSIST, MERGE, REMOVE, REFRESH, DETACH
    ProjetDetail projetDetail;  // Each project has one detail record

    @ManyToMany(mappedBy = "projets")  // Many-to-Many relationship with Equipe.
    // "mappedBy" indicates this is the inverse side (Equipe owns the relationship).
    // No join table created here because it's already created in Equipe class
    Set<Equipe> equipes;  // Set of teams working on this project (no duplicates)
}

// ======================================================================
// Database Structure:
// Table: table-Proj (custom name)
// Columns: id (PK), sujet
//
// One-to-One: projet_detail_id (FK to projet_detail table) - created by @OneToOne
//
// Many-to-Many: This is the inverse side (mappedBy="projets" in Equipe)
// The join table "equipe_projets" is created in Equipe class with columns:
// - equipe_id (FK to equipe)
// - projet_id (FK to projet)
//
// Relationships:
// - One projet -> One projetDetail (FK in projet table)
// - Many projets <-> Many equipes (join table owned by Equipe)
// ======================================================================