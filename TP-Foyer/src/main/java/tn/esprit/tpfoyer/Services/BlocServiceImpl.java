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
public class BlocServiceImpl implements IBlocServices{

    @Autowired
    private BlocRepository blocRepository;
    @Autowired
    private FoyerRepository foyerRepository;


    @Override
    public Bloc ajouterBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public List<Bloc> afficherBlocs() {
        return blocRepository.findAll();
    }

    @Override
    public Bloc afficherBlocSelonID(long idBloc) {
        return blocRepository.findById(idBloc).get();
    }

    @Override
    public Bloc modifierBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public void supprimerBloc(long idBloc) {
        blocRepository.deleteById(idBloc);
    }

    @Override
    public Bloc addBlocAndFoyer(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public void assignBlocToFoyer(long idBloc, long idFoyer) {
        Bloc bloc = blocRepository.findById(idBloc).get();
        Foyer foyer = foyerRepository.findById(idFoyer).get();

        foyer.getBlocs().add(bloc);
        foyerRepository.save(foyer);
    }

    @Override
    public void unassignBlocFromFoyer(long idBloc) {
        Bloc bloc = blocRepository.findById(idBloc).get();

        bloc.setFoyer(null);
        blocRepository.save(bloc);
    }
}
