package tn.esprit.tpfoyer.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    private Date anneeUniversitaire;
    private Boolean estValide;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore  // ← ADD THIS to break the loop
    Set<Etudiant> etudiants = new HashSet<Etudiant>();
}