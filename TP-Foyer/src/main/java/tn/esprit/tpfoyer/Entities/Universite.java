package tn.esprit.tpfoyer.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Universite  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idUniversite;

    String nomUniversite;
    String adresse;

    @OneToOne
    Foyer foyer;
}