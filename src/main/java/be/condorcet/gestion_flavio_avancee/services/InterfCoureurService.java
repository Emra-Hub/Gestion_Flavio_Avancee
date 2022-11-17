package be.condorcet.gestion_flavio_avancee.services;

import be.condorcet.gestion_flavio_avancee.entities.Coureur;
import be.condorcet.gestion_flavio_avancee.entities.Ville;

import java.util.List;

public interface InterfCoureurService extends InterfService<Coureur> {
    public List<Coureur> getCoureurs(Ville vi);

    public List<Coureur> read(String nationalite); // --> Critère non unique

    public List<Coureur> readUnique(String matricule); // --> Critère unique. Utilisé aussi pour les webservices
}
