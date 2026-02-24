package tn.esprit.tpfoyer.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.Entities.Foyer;
import tn.esprit.tpfoyer.Repositories.FoyerRepository;
import java.util.List;

@Service
@AllArgsConstructor
public class FoyerServiceImpl implements IFoyerServices {

    @Autowired
     private FoyerRepository foyerRepository;

    @Override
    public List<Foyer> afficherTousLesFoyers() {
        return foyerRepository.findAll();
    }

    @Override
    public Foyer afficherFoyer(Long idFoyer) {
        return foyerRepository.findById(idFoyer).orElse(null);
    }

    @Override
    public Foyer ajouterFoyer(Foyer foyer) {
        return foyerRepository.save(foyer);
    }

    @Override
    public Foyer modifierFoyer(Foyer foyer) {
        return foyerRepository.save(foyer);
    }

    @Override
    public void supprimerFoyer(Long idFoyer) {
        foyerRepository.deleteById(idFoyer);
    }
}