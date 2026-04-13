package tn.esprit.tpfoyer.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.Entities.Bloc;
import tn.esprit.tpfoyer.Entities.Foyer;
import tn.esprit.tpfoyer.Repositories.BlocRepository;
import tn.esprit.tpfoyer.Repositories.FoyerRepository;
import java.util.List;

@Service
@AllArgsConstructor
public class FoyerServiceImpl implements IFoyerServices {

    @Autowired
     private FoyerRepository foyerRepository;

    @Autowired
    private BlocRepository blocRepository;

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




    @Override
    public Foyer ajouterFoyerEtBloc(Foyer foyer) {
        return foyerRepository.save(foyer);   // le Bloc est inclus dans le JSON → cascade fait tout
    }

    @Override
    public void affecterBlocAFoyer(Long idBloc, Long idFoyer) {
        Foyer foyer = foyerRepository.findById(idFoyer).get();
        Bloc bloc = blocRepository.findById(idBloc).get();

        foyer.getBloc().add(bloc);
        bloc.setFoyer(foyer);               // ← AJOUT OBLIGATOIRE
        // on ajoute le fils au parent
        foyerRepository.save(foyer);
    }

    @Override
    public void desaffecterBlocDeFoyer(Long idBloc, Long idFoyer) {
        Foyer foyer = foyerRepository.findById(idFoyer).get();
        Bloc bloc = blocRepository.findById(idBloc).get();

        foyer.getBloc().remove(bloc);        // on enlève le fils du parent
        foyerRepository.save(foyer);
    }
}

