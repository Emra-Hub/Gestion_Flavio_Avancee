package be.condorcet.gestion_flavio_avancee.services;

import be.condorcet.gestion_flavio_avancee.entities.Ville;

import java.util.List;

public interface InterfVilleService extends InterfService<Ville> {
    public List<Ville> read(String nom); // --> Critère non unique

    public Ville readUnique(Double latitude, Double longitude); // --> Critère unique. Utilisé aussi pour les webservices

    //Question 2
    public List<Ville> readPays(String pays);
}
