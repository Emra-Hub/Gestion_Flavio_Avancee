package be.condorcet.gestion_flavio_avancee.services;

import be.condorcet.gestion_flavio_avancee.entities.Coureur;
import be.condorcet.gestion_flavio_avancee.entities.Ville;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoureurServiceImplTest {

    private Ville vi;
    private Coureur co;
    //private Coureur co2; // --> Pour tester le read sur le critère non unique
    @Autowired
    private VilleServiceImpl villeServiceImpl;

    @Autowired
    private CoureurServiceImpl coureurServiceImpl;

    @BeforeEach
    void setUp() {
        try{
            vi = new Ville(null,"NomTest",17.50,19.90,"PaysTest",new ArrayList<>());
            villeServiceImpl.create(vi);
            System.out.println("Création de la ville : "+vi);
            co = new Coureur(null,"MatriculeTest","NomCoureurTest","PrenomCoureurTest",Date.valueOf("2000-12-31"),"NationaliteTest",vi);
            //co2 = new Coureur(null,"MatriculeTest2","NomCoureurTest","PrenomCoureurTest",Date.valueOf("1998-05-19"),"NationaliteTest",vi);
            //coureurServiceImpl.create(co2);
            coureurServiceImpl.create(co);
            System.out.println("Création du coureur : "+co);
        }
        catch (Exception e){
            System.out.println("Erreur de création de coureur : "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
            coureurServiceImpl.delete(co);
            //coureurServiceImpl.delete(co2);
        }
        catch(Exception e) {
            System.out.println("Erreur de suppression du coureur : "+co+". Errerur : "+e);
        }
        try {
            villeServiceImpl.delete(vi);
        }
        catch(Exception e) {
            System.out.println("Erreur de suppression de la ville : "+vi+". Erreur : "+e);
        }
    }

    /*@Test
    void getCoureurs() {
    }*/

    @Test
    void read() { // --> Critère non unique
        List<Coureur> lco = coureurServiceImpl.read("NationaliteTest");
        boolean find = false;

        for (Coureur c : lco) {
            if(c.getNationalite().equals("NationaliteTest")) {
                find = true;
                System.out.println("Nationalité : "+c.getNationalite());
            }
            else fail("Aucun record ne correspond");
        }
        assertTrue(find,"Record non présent dans la liste");
    }

    @Test
    void readUnique() { // --> Critère unique
        List<Coureur> lco = coureurServiceImpl.readUnique("MatriculeTest");
        boolean find = false;

        for (Coureur c : lco) {
            if(c.getMatricule().contains("MatriculeTest")) {
                find = true;
                System.out.println("Matricule : "+c.getMatricule()+", Nom : "+c.getNom()+", Prenom : "+c.getPrenom());
            } else fail("Aucun record ne correspond");
        }
        assertTrue(find,"Record non présent dans la liste");
    }

    @Test
    void create() {
        assertNotEquals(0,co.getIdcoureur(),"ID coureur non incrémenté");
        assertEquals("MatriculeTest",co.getMatricule(),"Matricule coureur non enregistré : "+co.getMatricule()+" au lieu de MatriculeTest");
        assertEquals("NomCoureurTest",co.getNom(),"Nom coureur non enregistré : "+co.getNom()+" au lieu de NomCoureurTest");
        assertEquals("PrenomCoureurTest",co.getPrenom(),"Prenom coureur non enregistré : "+co.getPrenom()+" au lieu de PrenomCoureurTest");
        assertEquals(Date.valueOf("2000-12-31"),co.getDatenaiss(),"Date coureur non enregistrée : "+co.getDatenaiss()+" au lieu de 2000-12-31");
        assertEquals("NationaliteTest",co.getNationalite(),"Nationalite non enregistrée : "+co.getNationalite()+" au lieu de NationaliteTest");
        assertEquals(vi.getNom(),co.getVille().getNom(),"Ville non enregistrée : "+co.getVille().getNom()+" au lieu de "+vi.getNom());
    }

    @Test
    void testRead() {
        try {
            int numCo = co.getIdcoureur();
            Coureur co2 = coureurServiceImpl.read(numCo);
            assertEquals("MatriculeTest",co2.getMatricule(),"Matricule différents "+"MatriculeTest"+"-"+co2.getMatricule());
            assertEquals("NomCoureurTest",co2.getNom(),"Noms différents "+"NomCoureurTest"+"-"+co2.getNom());
            assertEquals("PrenomCoureurTest",co2.getPrenom(),"Prenoms différents "+"PrenomCoureurTest"+"-"+co2.getPrenom());
            assertEquals(Date.valueOf("2000-12-31"),co2.getDatenaiss(),"Date différentes "+"2000-12-31"+"-"+co2.getDatenaiss());
            assertEquals("NationaliteTest",co2.getNationalite(),"Nationalite différentes "+"NationaliteTest"+"-"+co2.getNationalite());
            assertEquals(vi.getNom(),co2.getVille().getNom(),"Ville différentes "+vi.getNom()+"-"+co2.getVille().getNom());
        }
        catch (Exception e){
            fail("Recherche infructueuse : "+e);
        }
    }

    @Test
    void update() {
        try{
            co.setMatricule("MatriculeTest2");
            co.setNom("NomCoureurTest2");
            co.setPrenom("PrenomCoureurTest2");
            co.setDatenaiss(Date.valueOf("1990-06-15"));
            co.setNationalite("NationaliteTest2");
            vi.setNom("NomTest2");
            vi = villeServiceImpl.update(vi);
            co = coureurServiceImpl.update(co);
            //System.out.println(co);
            assertEquals("MatriculeTest2",co.getMatricule(),"Matricule différents "+"MatriculeTest2"+"-"+co.getMatricule());
            assertEquals("NomCoureurTest2",co.getNom(),"Noms différents "+"NomCoureurTest2"+"-"+co.getNom());
            assertEquals("PrenomCoureurTest2",co.getPrenom(),"Prenoms différents "+"PrenomCoureurTest2"+"-"+co.getPrenom());
            assertEquals(Date.valueOf("1990-06-15"),co.getDatenaiss(),"Date différentes "+"1990-06-15"+"-"+co.getDatenaiss());
            assertEquals("NationaliteTest2",co.getNationalite(),"Nationalite différentes "+"NationaliteTest2"+"-"+co.getNationalite());
            assertEquals(vi.getNom(),co.getVille().getNom(),"Ville différentes "+vi.getNom()+"-"+co.getVille().getNom());
        }
        catch(Exception e) {
            fail("Erreur de mise à jour : "+e);
        }
    }

    @Test
    void delete() {
        try{
            coureurServiceImpl.delete(co);
            Assertions.assertThrows(Exception.class, () -> {
                coureurServiceImpl.read(co.getIdcoureur());
            },"Record non effacé");
        }
        catch(Exception e){
            fail("Erreur d'effacement : "+e);
        }
    }

    @Test
    void all() {
        try {
            List<Coureur> lco = coureurServiceImpl.all();
            assertNotEquals(0,lco.size(),"La liste ne contient aucun élément");
        } catch(Exception e) {
            fail("Erreur de recherche de toutes les villes : "+e);
        }
    }
}