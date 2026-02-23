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

public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idEntreprise;

    String nom;
    String adresse;

    //set bech enaa7i eli yt3awdoo
    //t3ml table associative 5ter famma set
    @OneToMany(mappedBy = "entreprise")
    Set<Equipe> equipes;
}
