package tn.esprit.tpprojet.Entities;

/**
 * Enum representing the different business domains in the application
 * Used to categorize projects and other entities by their business area
 */
public enum Domaine {

    ERPBI,
    SIM,
    NIDS
}

// ======================================================================
// Usage: @Enumerated(EnumType.STRING) in entities for database storage
// Example: private Domaine domaine;
// ======================================================================