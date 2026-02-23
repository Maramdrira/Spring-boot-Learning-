package tn.esprit.tpprojet.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity  // Marks this class as a JPA entity mapped to database table
@Getter  // Lombok: generates getters for all fields
@Setter  // Lombok: generates setters for all fields
@NoArgsConstructor  // Lombok: generates empty constructor
@AllArgsConstructor  // Lombok: generates constructor with all arguments
@FieldDefaults(level = AccessLevel.PRIVATE)  // Lombok: makes all fields private by default

public class ProjetDetail {
    @Id  // Marks this field as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment in database
    Long id;

    String description;  // Detailed description of the project
    String technologie;  // Technologies used in the project
    Long cout;  // Cost of the project (in TND, EUR, etc.)

    @Temporal(TemporalType.DATE)  // Specifies that Date should be stored as SQL DATE (year-month-day only, no time)
    // Other TemporalType options:
    // - TemporalType.TIME: stores only time (HH:MM:SS)
    // - TemporalType.TIMESTAMP: stores both date and time
    Date dateDebut;  // Project start date

    @OneToOne(mappedBy = "projetDetail")  // One-to-One relationship with Projet (inverse side)
    // "mappedBy = 'projetDetail'" means the Projet entity owns the relationship (as defined in Projet class)
    // The foreign key is in the Projet table (projet_detail_id), not here
    // No cascade here because the owning side (Projet) handles cascading operations
    // This should match exactly what we did in Projet class:
    // In Projet class we had: @OneToOne(cascade = CascadeType.ALL) ProjetDetail projetDetail;
    // So here we use mappedBy = "projetDetail" (the field name in Projet class)
    Projet projet;  // The project this detail belongs to (back reference)
}

// ======================================================================
// Database Structure:
// Table: projet_detail
// Columns:
// - id (PK, auto-increment)
// - description (VARCHAR)
// - technologie (VARCHAR)
// - cout (BIGINT)
// - dateDebut (DATE - year/month/day only)
//
// One-to-One Relationship:
// This is the inverse side of the One-to-One with Projet
// The foreign key "projet_detail_id" exists in the projet table
//
// Relationship Flow:
// - Projet entity owns the relationship (has @OneToOne with cascade)
// - ProjetDetail is the inverse side (has @OneToOne with mappedBy)
// - When you save a Projet with projetDetail, both are saved
// - When you delete a Projet, its ProjetDetail is also deleted (if cascade ALL)
// ======================================================================