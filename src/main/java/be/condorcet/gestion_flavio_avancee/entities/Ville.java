package be.condorcet.gestion_flavio_avancee.entities;

import lombok.*;

import javax.persistence.*;
@Data @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APIVILLE", schema = "ora23", catalog = "orcl")
public class Ville {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ville_generator")
    @SequenceGenerator(name="ville_generator", sequenceName = "APIVILLE_SEQ", allocationSize=1)
    private Integer idville;
    @NonNull
    private String nom;
    @NonNull
    private Double latitude;
    @NonNull
    private Double longitude;
    @NonNull
    private String pays;
}
