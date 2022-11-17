package be.condorcet.gestion_flavio_avancee.webservices;

import be.condorcet.gestion_flavio_avancee.entities.Ville;
import be.condorcet.gestion_flavio_avancee.services.InterfVilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
@RestController
@RequestMapping("/villes")
public class RestVille {

    @Autowired
    private InterfVilleService villeServiceImpl;

    //-------------------Retrouver la ville correspondante à un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Ville> getVille(@PathVariable(value = "id") int id)  throws Exception{
        System.out.println("recherche de la ville d' id " + id);
        Ville ville = villeServiceImpl.read(id);
        return new ResponseEntity<>(ville, HttpStatus.OK);
    }

    //-------------------Retrouver les villes portant un nom donné--------------------------------------------------------
    @RequestMapping(value = "/nom={nom}", method = RequestMethod.GET)
    public ResponseEntity<List<Ville>> listVillesNom(@PathVariable(value="nom") String nom) throws Exception{
        System.out.println("recherche de "+nom);
        List<Ville> villes;
        villes = villeServiceImpl.read(nom);
        return new ResponseEntity<>(villes, HttpStatus.OK);
    }

    //-------------------Retrouver la ville correspondante à des éléments uniques donnés (latitude et longitude)--------------------------------------------------------
    @RequestMapping(value = "/{latitude}/{longitude}", method = RequestMethod.GET)
    public ResponseEntity<Ville> getVilleUnique(@PathVariable(value = "latitude") Double latitude, @PathVariable(value = "longitude") Double longitude)  throws Exception{
        System.out.println("recherche de la ville "+latitude+" "+longitude);
        Ville ville = villeServiceImpl.readUnique(latitude,longitude);
        return new ResponseEntity<>(ville, HttpStatus.OK);
    }

    //-------------------Retrouver toutes les villes--------------------------------------------------------
    @RequestMapping(value =  "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Ville>> listVille() throws Exception{
        System.out.println("recherche de toutes les villes");
        return new ResponseEntity<>(villeServiceImpl.all(), HttpStatus.OK);
    }

    //-------------------Retrouver toutes les villes triées et par page--------------------------------------------------------
    @RequestMapping(value =  "/allp",method = RequestMethod.GET)
    public ResponseEntity<Page<Ville>> listVille(Pageable pageable) throws Exception{
        System.out.println("recherche de toutes les villes");
        return new ResponseEntity<>(villeServiceImpl.allp(pageable), HttpStatus.OK);
    }

    //-------------------Créer une ville--------------------------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Ville> createVille(@RequestBody Ville ville) throws Exception {
        System.out.println("Création de Ville " + ville.getNom());
        villeServiceImpl.create(ville);
        return new ResponseEntity<>(ville, HttpStatus.OK);
    }

    //-------------------Mettre à jour une ville d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Ville> majVille(@PathVariable(value = "id") int id,@RequestBody Ville nouvVille) throws Exception{
        System.out.println("maj de ville id =  " + id);
        nouvVille.setIdville(id);
        Ville viAct = villeServiceImpl.update(nouvVille);
        return new ResponseEntity<>(viAct, HttpStatus.OK);
    }

    //-------------------Effacer une ville d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteVille(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("effacement de la ville d'id " + id);
        Ville ville = villeServiceImpl.read(id);
        villeServiceImpl.delete(ville);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-------------------Gérer les erreurs--------------------------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }
}
