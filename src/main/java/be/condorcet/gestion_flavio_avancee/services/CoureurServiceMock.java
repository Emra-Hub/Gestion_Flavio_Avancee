package be.condorcet.gestion_flavio_avancee.services;

import be.condorcet.gestion_flavio_avancee.entities.Coureur;
import be.condorcet.gestion_flavio_avancee.entities.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CoureurServiceMock implements InterfCoureurService {

    private List<Coureur> lco = new ArrayList<>();

    private int idcoureur = 0;

    @Autowired
    private InterfVilleService villeServiceImpl;

    @Override
    public Coureur create(Coureur coureur) throws Exception {
        idcoureur++;
        coureur.setIdcoureur(idcoureur);
        Ville vi = coureur.getVille();
        if (vi.getCoureurs() == null) vi.setCoureurs(new ArrayList<>());
        vi.getCoureurs().add(coureur);
        lco.add(coureur);
        return coureur;
    }

    @Override
    public Coureur read(Integer id) throws Exception {
        for (Coureur co : lco) {
            if (co.getIdcoureur().equals(id)) return co;
        }
        throw new Exception("Code inconnu");
    }

    @Override
    public Coureur update(Coureur coureur) throws Exception {
        Integer id = coureur.getIdcoureur();
        Coureur oldCo = read(id);
        oldCo.setMatricule(coureur.getMatricule());
        oldCo.setNom(coureur.getNom());
        oldCo.setPrenom(coureur.getPrenom());
        oldCo.setDatenaiss(coureur.getDatenaiss());
        oldCo.setNationalite(coureur.getNationalite());
        return read(oldCo.getIdcoureur());
    }

    @Override
    public void delete(Coureur coureur) throws Exception {
        Iterator<Coureur> itc = lco.iterator();
        while (itc.hasNext()) {
            Coureur co = itc.next();
            if (co.getIdcoureur().equals(coureur.getIdcoureur())) {
                co.getVille().getCoureurs().remove(co);
                itc.remove();
            }
        }
    }

    @Override
    public List<Coureur> all() throws Exception {
        return lco;
    }

    @Override
    public List<Coureur> getCoureurs(Ville vi) {
        return (List<Coureur>) vi.getCoureurs();
    }

    @Override
    public List<Coureur> read(String nationalite) { // --> Critère non unique
        List<Coureur> lconom = new ArrayList<>();
        lco.stream().filter(co -> co.getNationalite().equals(nationalite)).forEach(co -> lconom.add(co));
        return lconom;
    }

    @Override
    public List<Coureur> readUnique(String matricule) {
        return (List<Coureur>) lco.stream().filter(co -> co.getMatricule().equals(matricule)).findFirst().get();
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
