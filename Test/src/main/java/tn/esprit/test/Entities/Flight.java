package tn.esprit.test.Entities;

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
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idFlight;

    String number;
    Float price ;
    @Enumerated(EnumType.STRING)
Status stutes;
    @Temporal(TemporalType.DATE)

    Date estimatedDep ;

    @Temporal(TemporalType.DATE)

  Date  realDep ;

@OneToMany(mappedBy = "Flight")
Set<Passenger> Passenger;

@ManyToOne
    Airline Airline;
}
