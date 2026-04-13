package tn.esprit.tpfoyer.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

public class Chambre  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChambre;
    private Long numeroChambre;

    @Enumerated(EnumType.STRING)
    private TypeChambre typeC;

    @JsonIgnore  // ← ADD THIS
    @ManyToOne(cascade = CascadeType.ALL)
    Bloc bloc;

    public Long getIdChambre() {
        return idChambre;
    }

    public Long getNumeroChambre() {
        return numeroChambre;
    }

    public TypeChambre getTypeC() {
        return typeC;
    }

    public Bloc getBloc() {
        return bloc;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    @OneToMany(cascade = CascadeType.ALL)
    Set<Reservation> reservations = new HashSet<Reservation>();
}
