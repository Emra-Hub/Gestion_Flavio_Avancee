package be.condorcet.gestion_flavio_avancee.services;

import be.condorcet.gestion_flavio_avancee.entities.Coureur;
import be.condorcet.gestion_flavio_avancee.entities.Ville;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VilleServiceStub implements InterfVilleService {
    @Override
    public Ville create(Ville ville) throws Exception {
        ville.setIdville(1);
        return ville;
    }

    @Override
    public Ville read(Integer id) throws Exception {
        Ville vi = new Ville(id,"NomTest",16.50,18.90,"PaysTest",new ArrayList<>());
        vi.getCoureurs().add(new Coureur(1,"MatriculeTest","NomCoureurTest","PrenomCoureurTest", Date.valueOf(LocalDate.now()),"NationaliteTest",vi));
        return vi;
    }

    @Override
    public Ville update(Ville ville) throws Exception {
        return ville;
    }

    @Override
    public void delete(Ville ville) throws Exception {
    }

    @Override
    public List<Ville> all() throws Exception {
        List<Ville> lvi = new ArrayList<>();
        lvi.add(new Ville(1,"Mons",16.50,18.90,"PaysTest", null));
        lvi.add(new Ville(2,"Charleroi",26.30,28.60,"PaysTest2", null));
        return lvi;
    }

    @Override
    public List<Ville> read(String nom) { // --> Critère non unique
        List<Ville> lvi = new ArrayList<>();
        lvi.add(new Ville(1,nom,16.50,18.90,"PaysTest", null));
        lvi.add(new Ville(2,nom,26.30,28.60,"PaysTest2", null));
        return lvi;
    }

    @Override // --> Critère unique. Utilisé aussi pour les webservices
    public Ville readUnique(Double latitude, Double longitude) {
        Ville vi = readUnique(latitude, longitude);
        vi.setIdville(1);
        return vi;
    }

    @Override // --> Pour les webservices
    public Page<Ville> allp(Pageable pageable) throws Exception {
        return null;
    }
}
