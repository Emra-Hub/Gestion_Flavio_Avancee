package be.condorcet.gestion_flavio_avancee.services;

import be.condorcet.gestion_flavio_avancee.entities.Ville;
import be.condorcet.gestion_flavio_avancee.repositories.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//@Service
@Transactional(rollbackOn = Exception.class)
public class VilleServiceImpl implements InterfVilleService {

    @Autowired
    private VilleRepository villeRepository;

    @Override // --> Critère non unique
    public List<Ville> read(String nom) { // --> Critère non unique
        return villeRepository.findVilleByNomLike(nom+"%");
    }

    @Override // --> Critère unique. Utilisé aussi pour les webservices
    public Ville readUnique(Double latitude, Double longitude) {
        return villeRepository.findVilleByLatitudeAndLongitude(latitude, longitude);
    }

    //Question 2
    @Override
    public List<Ville> readPays(String pays) {
        return villeRepository.findVilleByPaysLike(pays+"%");
    }

    @Override
    public Ville create(Ville ville) throws Exception {
        villeRepository.save(ville);
        return ville;
    }

    @Override
    public Ville read(Integer id) throws Exception {
        Optional<Ville> ovi = villeRepository.findById(id);
        return ovi.get();
    }

    @Override
    public Ville update(Ville ville) throws Exception {
        read((ville.getIdville()));
        villeRepository.save(ville);
        return ville;
    }

    @Override
    public void delete(Ville ville) throws Exception {
        villeRepository.deleteById(ville.getIdville());
    }

    @Override
    public List<Ville> all() throws Exception {
        return villeRepository.findAll();
    }

    @Override // --> Pour les webservices
    public Page<Ville> allp(Pageable pageable) throws Exception {
        return villeRepository.findAll(pageable);
    }
}
