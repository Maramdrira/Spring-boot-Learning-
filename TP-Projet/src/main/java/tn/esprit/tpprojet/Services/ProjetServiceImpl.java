package tn.esprit.tpprojet.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpprojet.Entities.Projet;
import tn.esprit.tpprojet.Repositories.ProjetRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjetServiceImpl implements IProjetServices{

   @Autowired
    private  ProjetRepository projetRepository;

    //injection par constructeur
    // private final ProjetRepository projetRepository;

    /*injection par constructeur
    private ProjetRepository(ProjetRepository pr) {
        this.projetRepository=pr;
    }*/


    //injection champ
    /*    private final ProjetRepository projetRepository;
          twalii
          @Autowired
          private ProjetRepository projetRepository;
     */


    //injection par setter
    /* ll repo mech ll service
    @Autowired

    private void setProjetRepository(ProjetRepository pr){
        this.projetRepository = pr;
    }
    */



    @Override
    public Projet ajouterProjet(Projet projet) {
        return projetRepository.save(projet);
    }

    //saveall mt3 list w save obj

    @Override
    public List<Projet> afficherProjet() {
        return projetRepository.findAll();
    }

    @Override
    public Projet afficherProjetSelonID(long idProjet) {
        return projetRepository.findById(idProjet).get();
    }
//get by id trj3 projet direct jawha behy ama bech yfs5ouha fel verion el jeya donc lawa7 nest3mo find by id mais trj3lnaa optinal<projet> ken ml9atouch trj3 .empty donc nzidoo .get() find all mtes7a9tech
    @Override
    public Projet modifierProjet(Projet projet) {
        return projetRepository.save(projet);
    }

    @Override
    public void supprimerProjet(long idProjet) {
    projetRepository.deleteById(idProjet);
    }
    //delte by id id fel url ama delete lezmha id fi wst obj bech y3rfhaa
}
