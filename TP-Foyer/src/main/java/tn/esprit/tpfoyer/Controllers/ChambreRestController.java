package tn.esprit.tpfoyer.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.Entities.Chambre;
import tn.esprit.tpfoyer.Services.IChambreServices;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Chambre")
public class ChambreRestController {

    @Autowired
    private IChambreServices chambreServices;

    @PostMapping("/addChambre")
    Chambre addChambre(@RequestBody Chambre chambre){
        return chambreServices.ajouterChambre(chambre);
    }

    @GetMapping("/getChambres")
    List<Chambre> getChambres(){
        return chambreServices.afficherChambres();
    }

    @GetMapping("/getChambreById/{idC}")
    Chambre getChambreById(@PathVariable("idC") long idChambre){
        return chambreServices.afficherChambreSelonID(idChambre);
    }

    @PutMapping("/updateChambre")
    Chambre updateChambre(@RequestBody Chambre chambre){
        return chambreServices.modifierChambre(chambre);
    }

    @DeleteMapping("/deleteChambre/{idC}")
    void DeleteChambre(@PathVariable("idC") long idChambre){
        chambreServices.supprimerChambre(idChambre);
    }

    @PostMapping("/ajouterChambreEtReservation")
    public Chambre addChambreAndReservation(@RequestBody Chambre chambre) {
        return chambreServices.addChambreAndReservation(chambre);
    }

    @PutMapping("/affecterReservationToChambre/{idChambre}/{idReservation}")
    public void assignReservationToChambre(@PathVariable("idChambre") long idChambre,
                                           @PathVariable("idReservation") long idReservation) {
        chambreServices.assignReservationToChambre(idChambre, idReservation);
    }

    @PutMapping("/desaffecterReservationDeChambre/{idChambre}/{idReservation}")
    public void unassignReservationFromChambre(@PathVariable("idChambre") long idChambre,
                                               @PathVariable("idReservation") long idReservation) {
        chambreServices.unassignReservationFromChambre(idChambre, idReservation);
    }
}
