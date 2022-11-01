package be.condorcet.gestion_flavio_avancee.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APICOUREUR", schema = "ora23", catalog = "orcl")
public class Coureur {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coureur_generator")
    @SequenceGenerator(name="coureur_generator", sequenceName = "APICOUREUR_SEQ", allocationSize=1)
    private Integer idcoureur;
    @NonNull
    private String matricule;
    @NonNull
    private String nom;
    @NonNull
    private String prenom;
    @NonNull
    private Date datenaiss;
    @NonNull
    private String nationalite;

    @NonNull
    @ManyToOne @JoinColumn(name = "IDVILLE_RESIDENCE")
    private Ville ville;
}
