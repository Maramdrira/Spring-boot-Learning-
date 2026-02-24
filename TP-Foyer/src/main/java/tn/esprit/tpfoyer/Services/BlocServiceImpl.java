package tn.esprit.tpfoyer.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.Entities.Bloc;
import tn.esprit.tpfoyer.Repositories.BlocRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BlocServiceImpl implements IBlocServices{

    @Autowired  // Tells Spring to inject the repository here
    private BlocRepository blocRepository;

    @Override
    public List<Bloc> afficherAllBlocs() {
        return blocRepository.findAll();

    }

    @Override
    public Bloc afficherBloc(Long idBloc) {
        return blocRepository.findById(idBloc).get();
    }

    @Override
    public Bloc ajouterBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public Bloc modifierBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public void removeBloc(Long idBloc) {
        blocRepository.deleteById(idBloc);

    }
}
