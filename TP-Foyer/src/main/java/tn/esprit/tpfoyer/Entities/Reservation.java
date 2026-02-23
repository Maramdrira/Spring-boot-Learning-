package tn.esprit.tpfoyer.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Reservation  {
    @Id
     Long idReservation;

    @Temporal(TemporalType.DATE)
    Date anneeUniversitaire;

    boolean estValide;


    @ManyToMany(cascade = CascadeType.ALL)
    Set<Etudiant> etudiants;

//mapped by file + cascade fel pere
}