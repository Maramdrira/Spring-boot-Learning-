package tn.esprit.tpprojet.Entities;


import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjetDetailDTO {
    String description;
    String technologie;
    String deteDebut;

}

