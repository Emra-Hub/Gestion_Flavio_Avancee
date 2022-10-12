package be.condorcet.gestion_flavio_avancee.repositories;

import be.condorcet.gestion_flavio_avancee.entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VilleRepository extends JpaRepository <Ville,Integer> {
}
