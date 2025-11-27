import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestPersonne {

    @Before
    public void setUp() throws SQLException {
        Personne.createTable();
        Personne p1 = new Personne("Spielberg", "Steven");
        Personne p2 = new Personne("Scott", "Ridley");
        Personne p3 = new Personne("Kubrick", "Stanley");
        Personne p4 = new Personne("Fincher", "David");
        p1.save();
        p2.save();
        p3.save();
        p4.save();
    }

    @Test
    public void testInsert() throws SQLException {
        Personne p1 = new Personne("Bastien","Phillipe");
        p1.save();
        Personne pres = Personne.findByNom("Bastien");
        assertEquals(pres.getNom(),p1.getNom());
        assertEquals(pres.getPrenom(),p1.getPrenom());
    }

    @Test
    public void test_FindbyID_OK() throws SQLException {
        Personne p1 = Personne.findById(1);
        assertEquals(p1.getPrenom(),"Steven");
    }

    @Test
    public void test_FindbyID_NOK() throws SQLException {
        Personne p1 = Personne.findById(30);
        assertNull(p1);
    }

    @Test
    public void test_FindbyNom_OK() throws SQLException {
        Personne p1 = Personne.findByNom("Scott");
        assertNotNull(p1);
        assertEquals("Scott", p1.getNom());
    }

    @Test
    public void test_FindAll() throws SQLException {
        ArrayList<Personne> personnes = Personne.findAll();
        assertEquals(personnes.getFirst().getPrenom(),"Steven");
    }

    @Test
    public void test_FindbyNom_NOK() throws SQLException {
        Personne p1 = Personne.findByNom("jscjvjksf");
        assertNull(p1);
    }


    @After
    public void tearDown() throws SQLException {
        Personne.deleteTable();
    }
}
