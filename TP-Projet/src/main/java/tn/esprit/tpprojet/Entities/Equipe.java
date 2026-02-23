package tn.esprit.tpprojet.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;

     String nom;

    @Enumerated(EnumType.STRING)
     Domaine domaine;

    @ManyToOne(cascade = CascadeType.ALL)
    Entreprise entreprise;


    //*   >1 : cle fel *  || *  >* : table as (set walla list) eli 3ndha vis
    @ManyToMany(cascade = CascadeType.ALL)
    Set<Projet> projets;
}
