package tn.esprit.tpprojet.Services;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.tpprojet.Entities.Projet;
import tn.esprit.tpprojet.Entities.ProjetDTO;

@Mapper(componentModel = "spring") //meth conversion bech y3rfha w twali bean w lezem tinjectiha bech t5dem
public interface MapperProjet {

    @Mapping(target = "libilee" , source = "sujet")
    ProjetDTO convertToDTO(Projet projet);

}
