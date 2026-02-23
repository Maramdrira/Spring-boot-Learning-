package tn.esprit.tpprojet.Entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "table-Proj")
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;

     String sujet;

    @OneToOne(cascade = CascadeType.ALL)
     ProjetDetail projetDetail;

    @ManyToMany(mappedBy = "projets")
    Set<Equipe> equipes ;
}
