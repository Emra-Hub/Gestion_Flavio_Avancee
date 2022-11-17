package be.condorcet.gestion_flavio_avancee.services;

import be.condorcet.gestion_flavio_avancee.entities.Ville;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VilleServiceImplTest {

    @Autowired
    private VilleServiceImpl villeServiceImpl;

    Ville vi;
    // Ville vi2; // --> Pour tester le read sur le critère non unique

    @BeforeEach
    void setUp() {
        try {
            vi = new Ville(null,"NomTest",16.50,18.90,"PaysTest",new ArrayList<>());
            //vi2 = new Ville(null,"NomTest",19.50,20.90,"PaysTest",new ArrayList<>());
            //villeServiceImpl.create(vi2);
            villeServiceImpl.create(vi);
            System.out.println("Création de la ville : "+vi);
        } catch(Exception e) {
            System.out.println("Erreur de création de la ville : "+vi+". Erreur : "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
            villeServiceImpl.delete(vi);
            //villeServiceImpl.delete(vi2);
            System.out.println("Suppression de la ville : "+vi);
        } catch(Exception e) {
            System.out.println("Erreur de suppression de la ville : "+vi+". Erreur : "+e);
        }
    }

    @Test
    void read() { // --> Critère non unique
        List<Ville> lvi = villeServiceImpl.read("NomTest");
        boolean find = false;

        for (Ville v : lvi) {
            if(v.getNom().startsWith("NomTest")) {
                find = true;
                System.out.println("Ville : "+v.getNom());
            }
            else fail("Aucun record ne correspond");
        }
        assertTrue(find,"Record non présent dans la liste");
    }

    @Test
    void readUnique() { // --> Critère unique
        List<Ville> lvi = (List<Ville>) villeServiceImpl.readUnique(16.50,18.90);
        boolean find = false;

        for (Ville v : lvi) {
            if(v.getLatitude().equals(16.50) && v.getLongitude().equals(18.90)) {
                find = true;
                System.out.println("Ville : "+v.getNom()+", Latitude : "+v.getLatitude()+", Longitude : "+v.getLongitude());
            } else fail("Aucun record ne correspond");
        }
        assertTrue(find,"Record non présent dans la liste");
    }

    @Test
    void create() {
        assertNotEquals(0,vi.getIdville(),"ID ville non incrémenté");
        assertEquals("NomTest",vi.getNom(),"Nom ville non enregistré : "+vi.getNom()+" au lieu de NomTest");
        assertEquals(16.50,vi.getLatitude(),"Latitude ville non enregistrée : "+vi.getLatitude()+" au lieu de 16.50");
        assertEquals(18.90,vi.getLongitude(),"Longitude non enregistrée : "+vi.getLongitude()+" au lieu de 18.90");
        assertEquals("PaysTest",vi.getPays(),"Pays non enregistré : "+vi.getPays()+" au lieu de PaysTest");
    }

    @Test
    void testRead() {
        try{
            int numVi = vi.getIdville();
            Ville vi2 = villeServiceImpl.read(numVi);
            assertEquals("NomTest",vi2.getNom(),"Noms différents "+"NomTest"+"-"+vi2.getNom());
            assertEquals(16.50,vi2.getLatitude(),"Latitudes différentes "+"16.50"+"-"+vi2.getLatitude());
            assertEquals(18.90,vi2.getLongitude(),"Longitudes différentes "+"18.90"+"-"+vi2.getLongitude());
            assertEquals("PaysTest",vi2.getPays(),"Pays différents "+"PaysTest"+"-"+vi2.getPays());
        }
        catch (Exception e){
            fail("Recherche infructueuse : "+e);
        }
    }

    @Test
    void update() {
        try{
            vi.setNom("NomTest2");
            vi.setLatitude(94.10);
            vi.setLongitude(95.20);
            vi.setPays("PaysTest2");
            vi = villeServiceImpl.update(vi);
            //System.out.println(vi);
            assertEquals("NomTest2",vi.getNom(),"Noms différents "+"NomTest2-"+vi.getNom());
            assertEquals(94.10,vi.getLatitude(),"Latitudes différentes "+"94.10-"+vi.getLatitude());
            assertEquals(95.20,vi.getLongitude(),"Longitudes différentes "+"95.20-"+vi.getLongitude());
            assertEquals("PaysTest2",vi.getPays(),"Pays différents "+"PaysTest2-"+vi.getPays());
        }
        catch(Exception e) {
            fail("Erreur de mise à jour : "+e);
        }
    }

    @Test
    void delete() {
        try {
            villeServiceImpl.delete(vi);
            Assertions.assertThrows(Exception.class, () -> {
                villeServiceImpl.read(vi.getIdville());
            },"Record non effacé");
        } catch (Exception e) {
            fail("Erreur d'effacement : "+e);
        }
    }

    @Test
    void all() {
        try {
            List<Ville> lvi = villeServiceImpl.all();
            assertNotEquals(0,lvi.size(),"La liste ne contient aucun élément");
        } catch(Exception e) {
            fail("Erreur de recherche de toutes les villes : "+e);
        }
    }
}