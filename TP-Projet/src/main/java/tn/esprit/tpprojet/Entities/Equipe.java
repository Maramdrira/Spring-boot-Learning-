package tn.esprit.tpprojet.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nom;

    @Enumerated(EnumType.STRING)  // Stores enum as string in DB (ex: "ERPBI" instead of 0,1,2).
    // Can also use EnumType.ORDINAL to store as integer (0,1,2) but less readable
    Domaine domaine;

    @ManyToOne(cascade = CascadeType.ALL)  // Many teams belong to one company.
    // Cascade ALL means any operation on team affects company.
    // Other cascade options: PERSIST, MERGE, REMOVE, REFRESH, DETACH
    Entreprise entreprise;

    @ManyToMany(cascade = CascadeType.ALL)  // Many teams can work on many projects.
    // Creates join table equipe_projets.

    Set<Projet> projets;  // Set ensures no duplicate projects.
    // Can also use List if order matters or duplicates allowed
}

// ======================================================================
// Database Structure:
// Table: equipe
// Columns: id (PK), nom, domaine (String), entreprise_id (FK to entreprise)
//
// Join Table: equipe_projets (for Many-to-Many)
// Columns: equipe_id (FK to equipe), projet_id (FK to projet)
//
// Relationships:
// - Many equipes -> One entreprise (FK in equipe table)
// - Many equipes <-> Many projets (join table)
// ======================================================================