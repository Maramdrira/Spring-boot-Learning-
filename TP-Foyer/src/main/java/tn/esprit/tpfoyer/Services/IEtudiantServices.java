package tn.esprit.tpfoyer.Services;

import tn.esprit.tpfoyer.Entities.Etudiant;
import java.util.List;

public interface IEtudiantServices {
    List<Etudiant> afficherTousLesEtudiants();
    Etudiant afficherEtudiant(Long idEtudiant);
    Etudiant ajouterEtudiant(Etudiant etudiant);
    Etudiant modifierEtudiant(Etudiant etudiant);
    void supprimerEtudiant(Long idEtudiant);
}