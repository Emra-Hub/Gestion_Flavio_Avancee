package be.condorcet.gestion_flavio_avancee.webservices;

import be.condorcet.gestion_flavio_avancee.entities.Coureur;
import be.condorcet.gestion_flavio_avancee.entities.Ville;
import be.condorcet.gestion_flavio_avancee.services.InterfCoureurService;
import be.condorcet.gestion_flavio_avancee.services.InterfVilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")  // --> Accepter que les webservices fonctionnent à partir d'autres sites dans l'en-tête.
@RestController
@RequestMapping("/coureurs")
public class RestCoureur {

    @Autowired
    private InterfCoureurService coureurServiceImpl;

    @Autowired
    private InterfVilleService villeServiceImpl;

    //-------------------Retrouver le coureur correspondant à un n° donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Coureur> getCoureur(@PathVariable(value = "id") int id)  throws Exception{
        System.out.println("recherche du coureur n° " + id);
        Coureur co = coureurServiceImpl.read(id);
        return new ResponseEntity<>(co, HttpStatus.OK);
    }

    //-------------------Retrouver le(s) coureur(s) correspondant à un n° de ville donné--------------------------------------------------------
    @RequestMapping(value = "/idville={id}", method = RequestMethod.GET)
    public ResponseEntity<List<Coureur>> getCoureurVille(@PathVariable(value = "id") int id)  throws Exception{
        System.out.println("recherche des coureurs de la ville d'id " + id);
        Ville vi = villeServiceImpl.read(id);
        List<Coureur> lco = coureurServiceImpl.getCoureurs(vi);
        return new ResponseEntity<>(lco, HttpStatus.OK);
    }

    //-------------------Créer un coureur--------------------------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Coureur> createCoureur(@RequestBody Coureur co) throws Exception {
        System.out.println("Création du coureur habitant à " + co.getVille());
        coureurServiceImpl.create(co);
        return new ResponseEntity<>(co, HttpStatus.OK);
    }

    //-------------------Mettre à jour un coureur d'un n° donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Coureur> majCoureur(@PathVariable(value = "id") int id,@RequestBody Coureur nouvco) throws Exception{
        System.out.println("maj du coureur n° " + id);
        nouvco.setIdcoureur(id);
        Coureur co = coureurServiceImpl.update(nouvco);
        return new ResponseEntity<>(co, HttpStatus.OK);
    }

    //-------------------Effacer un coureur d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCoureur(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("effacement du coureur n°" + id);
        Coureur co = coureurServiceImpl.read(id);
        coureurServiceImpl.delete(co);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-------------------Retrouver tous les coureurs --------------------------------------------------------
    @RequestMapping(value =  "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Coureur>> listCoureur() throws Exception{
        System.out.println("recherche de tous les coureurs");
        return new ResponseEntity<>(coureurServiceImpl.all(), HttpStatus.OK);
    }

    //-------------------Retrouver tous les coureurs paginés--------------------------------------------------------
    @RequestMapping(value =  "/allp",method = RequestMethod.GET)
    public ResponseEntity<Page<Coureur>> listCoureur(Pageable pageable) throws Exception{
        System.out.println("recherche de tous les coureurs");
        return new ResponseEntity<>(coureurServiceImpl.allp(pageable), HttpStatus.OK);
    }

    //-------------------Gérer les erreurs--------------------------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }
}
