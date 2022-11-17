package be.condorcet.gestion_flavio_avancee.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @JsonIgnore // --> Pour les webservices
    // @OneToMany(mappedBy = "ville" , fetch = FetchType.EAGER)
    //@OneToMany(mappedBy = "ville" , fetch = FetchType.LAZY,cascade=CascadeType.ALL, orphanRemoval=true)
    @OneToMany(mappedBy = "ville") // --> Pour les webservices
    //LAZY est la version par défaut
    //cascadeType.ALL permet d'effacer en cascade si le client disparaît
    // orphanRemoval=true permet d'ajouter et supprimer des commandes en DBà partir de la liste
    @ToString.Exclude
    private List<Coureur> coureurs;
}
