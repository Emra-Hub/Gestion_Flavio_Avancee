package be.condorcet.gestion_flavio_avancee.services;

import be.condorcet.gestion_flavio_avancee.entities.Ville;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VilleServiceMock implements InterfVilleService {

    private List<Ville> lvi = new ArrayList<>();

    private int idville = 0;

    @Override
    public Ville create(Ville vi) throws Exception {
        for (Ville vi2 : lvi) {
            if (vi2.getLatitude().equals(vi.getLatitude()) && (vi2.getLongitude().equals(vi.getLongitude())))
                throw new Exception("Doublon");
        }
        idville++;
        vi.setIdville(idville);
        lvi.add(vi);
        return vi;
    }

    @Override
    public Ville read(Integer id) throws Exception {
        for (Ville vi : lvi) {
            if (vi.getIdville().equals(id)) return vi;
        }
        throw new Exception("Code inconnu");
    }

    @Override
    public Ville update(Ville ville) throws Exception {
        Integer id = ville.getIdville();
        Ville oldVi = read(id);
        oldVi.setNom(ville.getNom());
        oldVi.setLatitude(ville.getLatitude());
        oldVi.setLongitude(ville.getLongitude());
        oldVi.setPays(ville.getPays());
        return read(oldVi.getIdville());
    }

    @Override
    public void delete(Ville viDel) throws Exception {
        Iterator<Ville> itc = lvi.iterator();
        while (itc.hasNext()) {
            Ville vi = itc.next();
            if (vi.getIdville().equals(viDel.getIdville())) {
                if (vi.getCoureurs() == null || vi.getCoureurs().isEmpty())
                    itc.remove();
                else throw new Exception("Liste de coureurs non vide");
            }
        }
    }

    @Override
    public List<Ville> all() throws Exception {
        return lvi;
    }

    @Override
    public List<Ville> read(String nom) { // --> Critère non unique
        List<Ville> lvinom = new ArrayList<>();
        lvi.stream().filter(vi -> vi.getNom().equals(nom)).forEach(vi -> lvinom.add(vi));
        return lvinom;
    }

    @Override
    public List<Ville> readUnique(Double latitude, Double longitude) {
        return (List<Ville>) lvi.stream().filter(vi -> vi.getLatitude().equals(latitude) && vi.getLongitude().equals(longitude)).findFirst().get();
    }
}
