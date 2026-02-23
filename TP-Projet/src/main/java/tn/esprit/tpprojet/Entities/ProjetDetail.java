package tn.esprit.tpprojet.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ProjetDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;

     String description;
     String technologie;
     Long cout;

    @Temporal(TemporalType.DATE)
     Date dateDebut;


    @OneToOne(mappedBy = "projetDetail") //kima kifh samitha fel projet
    Projet projet;
}
