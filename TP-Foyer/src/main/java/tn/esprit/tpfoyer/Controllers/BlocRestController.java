package tn.esprit.tpfoyer.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.Entities.Bloc;
import tn.esprit.tpfoyer.Services.IBlocServices;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Bloc")
public class BlocRestController {

    @Autowired
    private IBlocServices blocServices;

    @PostMapping("/addBloc")
    Bloc addBloc(@RequestBody Bloc bloc){
        return blocServices.ajouterBloc(bloc);
    }

    @GetMapping("/getBlocs")
    List<Bloc> getBlocs(){
        return blocServices.afficherBlocs();
    }

    @GetMapping("/getBlocById/{idB}")
    Bloc getBlocById(@PathVariable("idB") long idBloc){
        return blocServices.afficherBlocSelonID(idBloc);
    }

    @PutMapping("/updateBloc")
    Bloc updateBloc(@RequestBody Bloc bloc){
        return blocServices.modifierBloc(bloc);
    }

    @DeleteMapping("/deleteBloc/{idB}")
    void DeleteBloc(@PathVariable("idB") long idBloc){
        blocServices.supprimerBloc(idBloc);
    }

    @PostMapping("/ajouterBlocEtFoyer")
    public Bloc addBlocAndFoyer(@RequestBody Bloc bloc) {
        return blocServices.addBlocAndFoyer(bloc);
    }

    @PutMapping("/affecterBlocToFoyer/{idBloc}/{idFoyer}")
    public void assignBlocToFoyer(@PathVariable("idBloc") long idBloc,
                                  @PathVariable("idFoyer") long idFoyer) {
        blocServices.assignBlocToFoyer(idBloc, idFoyer);
    }

    @PutMapping("/desaffecterBlocDeFoyer/{idBloc}")
    public void unassignBlocFromFoyer(@PathVariable("idBloc") long idBloc) {
        blocServices.unassignBlocFromFoyer(idBloc);
    }
}
