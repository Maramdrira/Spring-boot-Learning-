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

public class Bloc  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBloc;
    private String nomBloc;
    private Long capaciteBloc;

    @ManyToOne(cascade = CascadeType.ALL)
    Foyer foyer;

    @OneToMany(mappedBy = "bloc")
    Set<Chambre> chambres = new HashSet<Chambre>();

    public Long getIdBloc() {
        return idBloc;
    }

    public String getNomBloc() {
        return nomBloc;
    }

    public Long getCapaciteBloc() {
        return capaciteBloc;
    }

    public Foyer getFoyer() {
        return foyer;
    }

    public Set<Chambre> getChambres() {
        return chambres;
    }
}
