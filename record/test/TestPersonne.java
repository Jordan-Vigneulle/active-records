import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class TestPersonne {
    @BeforeEach
    public void setUp() throws SQLException {
        Personne.createTable();
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
        assertEquals(p1.getNom(),"Scott");
    }

    @Test
    public void test_FindbyNom_NOK() throws SQLException {
        Personne p1 = Personne.findByNom("jscjvjksf");
        assertNull(p1);
    }


    @AfterEach
    public void tearDown() throws SQLException {
        Personne.deleteTable();
    }
}
