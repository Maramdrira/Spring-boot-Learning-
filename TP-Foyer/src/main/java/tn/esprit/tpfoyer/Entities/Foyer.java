package tn.esprit.tpfoyer.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Foyer  {
    public Long getIdFoyer() {
        return idFoyer;
    }

    public String getNomFoyer() {
        return nomFoyer;
    }

    public Long getCapaciteFoyer() {
        return capaciteFoyer;
    }

    public Universite getUniversite() {
        return universite;
    }

    public Set<Bloc> getBloc() {
        return blocs;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFoyer;
    private String nomFoyer;
    private Long capaciteFoyer;

    @OneToOne(mappedBy = "foyer")
    Universite universite;

    @OneToMany(mappedBy = "foyer")
    Set<Bloc> blocs = new HashSet<Bloc>();
}
