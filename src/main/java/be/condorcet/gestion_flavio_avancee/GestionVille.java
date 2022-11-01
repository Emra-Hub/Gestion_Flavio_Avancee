package be.condorcet.gestion_flavio_avancee;

import be.condorcet.gestion_flavio_avancee.entities.Ville;
import be.condorcet.gestion_flavio_avancee.repositories.VilleRepository;
import be.condorcet.gestion_flavio_avancee.services.VilleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/villes")
public class GestionVille {
    /*@Autowired
    private VilleRepository villeRepository;*/

    @Autowired
    private VilleServiceImpl villeServiceImpl;

    @RequestMapping("/tous")
    public String affAll(Map<String, Object> model) {
        System.out.println("Affichage des villes");
        try {
            // Collection<Ville> lvi= villeRepository.findAll();

            Collection<Ville> lvi= villeServiceImpl.all();
            model.put("mesVilles",lvi);
        } catch (Exception e) {
            System.out.println("----------Erreur lors de la recherche-------- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "affichagetoutesVilles";
    }

    @RequestMapping("/create")
    public String create(@RequestParam String nom, @RequestParam Double latitude, @RequestParam Double longitude, @RequestParam String pays, Map<String, Object> model){
        System.out.println("Création d'une ville");
        Ville vi = new Ville(nom,latitude,longitude, pays);
        try {
            // villeRepository.save(vi);//mise à jour de la ville avec son id par l'environnement
            // System.out.println(vi);
            // Collection<Ville> lvi= villeRepository.findAll();

            villeServiceImpl.create(vi); //mise à jour de la ville avec son id par l'environnement
            vi = villeServiceImpl.read(vi.getIdville());
            villeServiceImpl.update(vi);
            model.put("nouvVille",vi);
        } catch (Exception e) {
            System.out.println("----------Erreur lors de la création-------- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "newVille";
    }

    @RequestMapping("/search")
    public String search(@RequestParam Integer idville, Map<String, Object> model) {
        System.out.println("Recherche de la ville n° "+idville);
        try {
            /*Optional<Ville> ovi = villeRepository.findById(idville);
            ovi.ifPresent((vi) -> {
                vi = ovi.get();
                model.put("rechVille",vi);
            });

            if (ovi.isEmpty()) {
                throw new Exception("Cet ID n'existe pas.");
            }*/

            Ville vi = villeServiceImpl.read(idville);
            model.put("rechVille",vi);
        } catch (Exception e) {
            System.out.println("----------Erreur lors de la recherche-------- " + e);
            model.put("error","Erreur : "+e.getMessage());
            return "error";
        }
        return "searchVille";
    }

    @RequestMapping("/update")
    public String update(@RequestParam Integer idville, @RequestParam String nom, @RequestParam Double latitude, @RequestParam Double longitude, @RequestParam String pays, Map<String, Object> model) {
        System.out.println("Modification de la ville n° "+idville);
        try {
            /*Optional<Ville> ovi = villeRepository.findById(idville);
            ovi.ifPresent((vi) -> {
                vi = ovi.get();
                vi.setNom(nom);
                vi.setLatitude(latitude);
                vi.setLongitude(longitude);
                vi.setPays(pays);
                villeRepository.save(vi);
                model.put("majVille",vi);
            });*/

            Ville vi = villeServiceImpl.read(idville);
            vi.setNom(nom);
            vi.setLatitude(latitude);
            vi.setLongitude(longitude);
            vi.setPays(pays);
            villeServiceImpl.update(vi);
            model.put("majVille",vi);

        } catch (Exception e) {
            System.out.println("----------Erreur lors de la modification-------- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "updateVille";
    }

    @RequestMapping("/delete")
    public String delete (@RequestParam Integer idville, Map<String, Object> model) {
        System.out.println("Suppression de la ville n° "+idville);
        try {
            /*Optional<Ville> ovi = villeRepository.findById(idville);
            ovi.ifPresent((vi) -> {
                vi = ovi.get();
                villeRepository.deleteById(idville);
                model.put("supVille",vi);
            });

            if (ovi.isEmpty()) {
                throw new Exception("Cet ID n'existe pas.");
            }*/

            Ville vi = villeServiceImpl.read(idville);
            villeServiceImpl.delete(vi);
            model.put("supVille",vi);
        } catch (Exception e) {
            System.out.println("----------Erreur lors de la recherche-------- " + e);
            model.put("error","Erreur : "+e.getMessage());
            return "error";
        }
        return "deleteVille";
    }

    @RequestMapping("/read")
    public String read(@RequestParam int idville, Map<String, Object> model) {
        System.out.println("Recherche de la ville n° "+idville);
        try {
            Ville vi = villeServiceImpl.read(idville);
            model.put("maVille",vi);
        }catch (Exception e) {
            System.out.println("----------Erreur lors de la recherche-------- " + e);
            model.put("error","Erreur : "+e.getMessage());
            return "error";
        }
        return "affichageVille";
    }
}