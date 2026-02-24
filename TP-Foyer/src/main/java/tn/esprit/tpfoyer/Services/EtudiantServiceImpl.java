package tn.esprit.tpfoyer.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.Entities.Etudiant;
import tn.esprit.tpfoyer.Repositories.EtudiantRepository;
import java.util.List;

@Service
@AllArgsConstructor
public class EtudiantServiceImpl implements IEtudiantServices {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Override
    public List<Etudiant> afficherTousLesEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant afficherEtudiant(Long idEtudiant) {
        return etudiantRepository.findById(idEtudiant).orElse(null);
    }

    @Override
    public Etudiant ajouterEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    @Override
    public Etudiant modifierEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    @Override
    public void supprimerEtudiant(Long idEtudiant) {
        etudiantRepository.deleteById(idEtudiant);
    }
}