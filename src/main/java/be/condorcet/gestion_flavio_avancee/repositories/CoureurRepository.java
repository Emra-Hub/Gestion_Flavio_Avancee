package be.condorcet.gestion_flavio_avancee.repositories;

import be.condorcet.gestion_flavio_avancee.entities.Coureur;
import be.condorcet.gestion_flavio_avancee.entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoureurRepository extends JpaRepository<Coureur,Integer>
{
    public List<Coureur> findCoureurByVille(Ville vi);

    public List<Coureur> findCoureurByNationalite(String nationalite); // --> Critère non unique

    public List<Coureur> findCoureurByMatricule(String matricule); // --> Critère unique

    // Question 1
    public List<Coureur> findCoureurByNomLike(String nom);

}
