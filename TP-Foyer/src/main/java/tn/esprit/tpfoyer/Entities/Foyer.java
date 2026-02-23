package tn.esprit.tpfoyer.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Foyer  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long idFoyer;

     String nomFoyer;
     Long capaciteFoyer;

     @OneToOne(mappedBy = "foyer")
    Universite universite;

     @OneToMany(mappedBy = "foyer")
     Set<Bloc> Bloc;
}