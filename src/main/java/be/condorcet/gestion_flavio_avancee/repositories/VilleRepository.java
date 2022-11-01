package be.condorcet.gestion_flavio_avancee.repositories;

import be.condorcet.gestion_flavio_avancee.entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VilleRepository extends JpaRepository <Ville,Integer> {
    List<Ville> findVilleByNomLike(String nom); // --> Critère non unique

    List<Ville> findVilleByLatitudeAndLongitude(Double latitude, Double longitude); // --> Critère unique
}
