package be.condorcet.gestion_flavio_avancee.services;

import be.condorcet.gestion_flavio_avancee.entities.Coureur;
import be.condorcet.gestion_flavio_avancee.entities.Ville;
import be.condorcet.gestion_flavio_avancee.repositories.CoureurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

//@Service
@Transactional(rollbackOn = Exception.class)
public class CoureurServiceImpl implements InterfCoureurService {

    @Autowired
    private CoureurRepository coureurRepository;

    @Override
    public List<Coureur> getCoureurs(Ville vi) {
        List<Coureur> lco = coureurRepository.findCoureurByVille(vi);
        return lco;
    }

    @Override
    public List<Coureur> read(String nationalite) { // --> Critère non unique
        return coureurRepository.findCoureurByNationalite(nationalite);
    }

    @Override
    public List<Coureur> readUnique(String matricule) { // --> Critère unique
        return coureurRepository.findCoureurByMatricule(matricule);
    }

    @Override
    public Coureur create(Coureur coureur) throws Exception {
        coureurRepository.save(coureur);
        return coureur;
    }

    @Override
    public Coureur read(Integer id) throws Exception {
        return coureurRepository.findById(id).get();
    }

    @Override
    public Coureur update(Coureur coureur) throws Exception {
        read(coureur.getIdcoureur());
        coureurRepository.save(coureur);
        return coureur;
    }

    @Override
    public void delete(Coureur coureur) throws Exception {
        coureurRepository.deleteById(coureur.getIdcoureur());
    }

    @Override
    public List<Coureur> all() throws Exception {
        return coureurRepository.findAll();
    }

    @Override // --> Pour les websevices
    public Page<Coureur> allp(Pageable pageable) throws Exception {
        return coureurRepository.findAll(pageable);
    }
}
