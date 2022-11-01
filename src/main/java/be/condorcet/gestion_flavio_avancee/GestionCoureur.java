package be.condorcet.gestion_flavio_avancee;

import be.condorcet.gestion_flavio_avancee.entities.Coureur;
import be.condorcet.gestion_flavio_avancee.entities.Ville;
import be.condorcet.gestion_flavio_avancee.services.CoureurServiceImpl;
import be.condorcet.gestion_flavio_avancee.services.VilleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/coureurs")
public class GestionCoureur {
    @Autowired
    private CoureurServiceImpl coureurServiceImpl;
    @Autowired
    private VilleServiceImpl villeServiceImpl;

    @RequestMapping("/tous")
    public String affAll(Map<String, Object> model) {
        System.out.println("Affichage des coureurs");
        try {
            Collection<Coureur> lco = coureurServiceImpl.all();
            model.put("mesCoureurs",lco);
        } catch (Exception e) {
            System.out.println("----------Erreur lors de la recherche-------- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "affichagetousCoureurs";
    }

    @RequestMapping("/searchByVille")
    public String search(@RequestParam Integer idville, Map<String, Object> model) {
        System.out.println("Recherche de la ville n° " + idville);
        try {
            Ville vi = villeServiceImpl.read(idville);
            List<Coureur> lco = coureurServiceImpl.getCoureurs(vi);
            model.put("maVille",vi);
            model.put("maVilleCoureurs",lco);
        }catch (Exception e) {
            System.out.println("----------Erreur lors de la recherche-------- " + e);
            model.put("error","Erreur : "+e.getMessage());
            return "error";
        }
        return "affichageVilleCoureur";
    }
}
