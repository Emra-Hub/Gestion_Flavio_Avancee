package be.condorcet.gestion_flavio_avancee.services;

import be.condorcet.gestion_flavio_avancee.entities.Ville;
import be.condorcet.gestion_flavio_avancee.repositories.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//@Service
@Transactional(rollbackOn = Exception.class)
public class VilleServiceImpl implements InterfVilleService {

    @Autowired
    private VilleRepository villeRepository;

    @Override
    public List<Ville> read(String nom) { // --> Critère non unique
        return villeRepository.findVilleByNomLike(nom+"%");
    }

    @Override
    public List<Ville> readUnique(Double latitude, Double longitude) { // --> Critère unique
        return villeRepository.findVilleByLatitudeAndLongitude(latitude, longitude);
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
}
