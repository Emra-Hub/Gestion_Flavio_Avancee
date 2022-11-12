package be.condorcet.gestion_flavio_avancee;

import be.condorcet.gestion_flavio_avancee.entities.Coureur;
import be.condorcet.gestion_flavio_avancee.entities.Ville;
import be.condorcet.gestion_flavio_avancee.services.CoureurServiceImpl;
import be.condorcet.gestion_flavio_avancee.services.InterfCoureurService;
import be.condorcet.gestion_flavio_avancee.services.InterfVilleService;
import be.condorcet.gestion_flavio_avancee.services.VilleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/coureurs")
public class GestionCoureur {
    /*@Autowired
    private CoureurServiceImpl coureurServiceImpl;
    @Autowired
    private VilleServiceImpl villeServiceImpl;*/

    @Autowired
    private InterfVilleService villeServiceImpl;

    @Autowired
    private InterfCoureurService coureurServiceImpl;

    @RequestMapping("/tous")
    public String affAll(Map<String, Object> model) {
        System.out.println("Affichage des coureurs");
        try {
            Collection<Coureur> lco = coureurServiceImpl.all();
            model.put("mesCoureurs",lco);
        } catch (Exception e) {
            System.out.println("----------Erreur lors de la recherche-------- " + e);
            model.put("error","Erreur : "+e.getMessage());
            return "error";
        }
        return "affichagetousCoureurs";
    }

    @RequestMapping("/searchByVille")
    public String searchByVille(@RequestParam Integer idville, Map<String, Object> model) {
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

    @RequestMapping("/create")
    public String create(@RequestParam String matricule, @RequestParam String nom, @RequestParam String prenom, @RequestParam Date datenaiss, @RequestParam String nationalite, @RequestParam Ville ville, Map<String, Object> model){
        System.out.println("Création d'un coureur");
        Coureur co = new Coureur(matricule,nom,prenom,datenaiss,nationalite,ville);
        try {
            coureurServiceImpl.create(co); //mise à jour du coureur avec son id par l'environnement
            co = coureurServiceImpl.read(co.getIdcoureur());
            coureurServiceImpl.update(co);
            model.put("nouvCoureur",co);
        } catch (Exception e) {
            System.out.println("----------Erreur lors de la création-------- " + e);
            model.put("error","Erreur : "+e.getMessage());
            return "error";
        }
        return "newCoureur";
    }

    @RequestMapping("/search")
    public String search(@RequestParam Integer idcoureur, Map<String, Object> model) {
        System.out.println("Recherche du coureur n° "+idcoureur);
        try {
            Coureur co = coureurServiceImpl.read(idcoureur);
            model.put("rechCoureur",co);
        } catch (Exception e) {
            System.out.println("----------Erreur lors de la recherche-------- " + e);
            model.put("error","Erreur : "+e.getMessage());
            return "error";
        }
        return "searchCoureur";
    }

    @RequestMapping("/update")
    public String update(@RequestParam Integer idcoureur, @RequestParam String matricule, @RequestParam String nom, @RequestParam String prenom, @RequestParam Date datenaiss, @RequestParam String nationalite, @RequestParam Ville ville, Map<String, Object> model) {
        System.out.println("Modification du coureur n° "+idcoureur);
        try {
            Coureur co = coureurServiceImpl.read(idcoureur);
            co.setMatricule(matricule);
            co.setNom(nom);
            co.setPrenom(prenom);
            co.setDatenaiss(datenaiss);
            co.setNationalite(nationalite);
            co.setVille(ville);
            coureurServiceImpl.update(co);
            model.put("majCoureur",co);

        } catch (Exception e) {
            System.out.println("----------Erreur lors de la modification-------- " + e);
            model.put("error","Erreur : "+e.getMessage());
            return "error";
        }
        return "updateCoureur";
    }

    @RequestMapping("/delete")
    public String delete (@RequestParam Integer idcoureur, Map<String, Object> model) {
        System.out.println("Suppression du coureur n° "+idcoureur);
        try {
            Coureur co = coureurServiceImpl.read(idcoureur);
            coureurServiceImpl.delete(co);
            model.put("supCoureur",co);
        } catch (Exception e) {
            System.out.println("----------Erreur lors de la recherche-------- " + e);
            model.put("error","Erreur : "+e.getMessage());
            return "error";
        }
        return "deleteCoureur";
    }
}
