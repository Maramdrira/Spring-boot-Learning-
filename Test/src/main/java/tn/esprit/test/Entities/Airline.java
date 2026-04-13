package tn.esprit.test.Entities;

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
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idAirline;

    String code;
    String name;

    @OneToMany(mappedBy = "Airline")
    Set<Flight> Flight;

}
