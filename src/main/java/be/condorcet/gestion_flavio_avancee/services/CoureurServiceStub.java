package be.condorcet.gestion_flavio_avancee.services;

import be.condorcet.gestion_flavio_avancee.entities.Coureur;
import be.condorcet.gestion_flavio_avancee.entities.Ville;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CoureurServiceStub implements InterfCoureurService {

    @Override
    public Coureur create(Coureur coureur) throws Exception {
        coureur.setIdcoureur(1);
        return coureur;
    }

    @Override
    public Coureur read(Integer id) throws Exception {
        return new Coureur(id,"MatriculeTest","NomCoureurTest","PrenomCoureurTest", Date.valueOf(LocalDate.now()),"NationaliteTest",new Ville(1,"NomTest",16.50,18.90,"PaysTest",null));
    }

    @Override
    public Coureur update(Coureur coureur) throws Exception {
        return coureur;
    }

    @Override
    public void delete(Coureur coureur) throws Exception {
    }

    @Override
    public List<Coureur> all() throws Exception {
        List<Coureur> lco = new ArrayList<>();
        lco.add(new Coureur(1,"MatriculeTest","NomCoureurTest","PrenomCoureurTest", Date.valueOf(LocalDate.now()),"NationaliteTest",new Ville(1,"NomTest",16.50,18.90,"PaysTest",null)));
        lco.add(new Coureur(2,"MatriculeTest2","NomCoureurTest2","PrenomCoureurTest2", Date.valueOf(LocalDate.now()),"NationaliteTest2",new Ville(2,"NomTest2",26.30,28.60,"PaysTest2",null)));
        return lco;
    }

    @Override
    public List<Coureur> getCoureurs(Ville vi) {
        List<Coureur> lco = new ArrayList<>();
        lco.add(new Coureur(1,"MatriculeTest","NomCoureurTest","PrenomCoureurTest", Date.valueOf(LocalDate.now()),"NationaliteTest",vi));
        lco.add(new Coureur(2,"MatriculeTest2","NomCoureurTest2","PrenomCoureurTest2", Date.valueOf(LocalDate.now()),"NationaliteTest2",vi));
        return lco;
    }

    @Override
    public List<Coureur> read(String nationalite) { // --> Critère non unique
        List<Coureur> lco = new ArrayList<>();
        lco.add(new Coureur(1,"MatriculeTest","NomCoureurTest","PrenomCoureurTest", Date.valueOf(LocalDate.now()),nationalite,null));
        lco.add(new Coureur(2,"MatriculeTest2","NomCoureurTest2","PrenomCoureurTest2", Date.valueOf(LocalDate.now()),nationalite,null));
        return lco;
    }

    @Override
    public List<Coureur> readUnique(String matricule) {
        Coureur co = (Coureur) readUnique(matricule);
        co.setIdcoureur(1);
        return (List<Coureur>) co;
    }

    // Question 1
    @Override
    public List<Coureur> readNom(String nom) {
        return null;
    }

    @Override // --> Pour les webservices
    public Page<Coureur> allp(Pageable pageable) throws Exception {
        return null;
    }
}
