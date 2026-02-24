package tn.esprit.tpfoyer.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.Entities.Chambre;
import tn.esprit.tpfoyer.Repositories.ChambreRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ChambreServiceImpl implements IChambreServices {
    @Autowired
    private ChambreRepository chambreRepository;


    @Override
    public List<Chambre> afficherAllChambres() {
        return chambreRepository.findAll();
    }

    @Override
    public Chambre afficherChambre(Long idChambre) {
        return chambreRepository.findById(idChambre).orElse(null);
    }

    @Override
    public Chambre ajouterChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public Chambre modifierChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public void supprimerChambre(Long idChambre) {
        chambreRepository.deleteById(idChambre);

    }
}
