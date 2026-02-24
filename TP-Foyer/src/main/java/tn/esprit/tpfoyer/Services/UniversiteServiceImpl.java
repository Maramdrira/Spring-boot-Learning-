package tn.esprit.tpfoyer.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.Entities.Universite;
import tn.esprit.tpfoyer.Repositories.UniversiteRepository;
import java.util.List;

@Service
@AllArgsConstructor
public class UniversiteServiceImpl implements IUniversiteServices {
    @Autowired
     private UniversiteRepository universiteRepository;

    @Override
    public List<Universite> afficherToutesLesUniversites() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite afficherUniversite(Long idUniversite) {
        return universiteRepository.findById(idUniversite).orElse(null);
    }

    @Override
    public Universite ajouterUniversite(Universite universite) {
        return universiteRepository.save(universite);
    }

    @Override
    public Universite modifierUniversite(Universite universite) {
        return universiteRepository.save(universite);
    }

    @Override
    public void supprimerUniversite(Long idUniversite) {
        universiteRepository.deleteById(idUniversite);
    }
}