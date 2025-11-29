import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class FilmTest {

    @Before
    public void setUp() throws SQLException {
        Personne.createTable();
        Film.createTable();
        Personne p1 = new Personne("Spielberg", "Steven");
        Personne p2 = new Personne("Scott", "Ridley");
        Personne p3 = new Personne("Kubrick", "Stanley");
        Personne p4 = new Personne("Fincher", "David");
        p1.save();
        p2.save();
        p3.save();
        p4.save();
        Film f1 = new Film("Titanic", 1);
        Film f2 = new Film("Les as de la jungle", 2);
        Film f3 = new Film("Kong king", 3);
        Film f4 = new Film("Les lapins mais intelligent !", 4);
        Film f5 = new Film("Les as, le retour", 2);
        f1.save();
        f2.save();
        f3.save();
        f4.save();
        f5.save();
    }

    @Test
    public void insertFilmTest() throws SQLException {
        Film f = new Film("Inception", 3);
        f.save();
        Film res = Film.findById(6);
        assertNotNull(res);
        assertEquals("Inception", res.getTitre());
        assertEquals(3, res.getRealisateur().getId());
    }

    @Test
    public void findByIdTest_OK() throws SQLException {
        Film f = Film.findById(2);
        assertNotNull(f);
        assertEquals("Les as de la jungle", f.getTitre());
    }

    @Test
    public void findByIdTest_NOK() throws SQLException {
        Film f = Film.findById(999);
        assertNull(f);
    }

    @Test
    public void getRealisateurTest() throws SQLException {
        Film f = Film.findById(1);
        assertNotNull(f);
        Personne rea = f.getRealisateur();
        assertEquals("Spielberg", rea.getNom());
    }

    @Test
    public void insertFilmWithAbsentRealisateurTest() {
        Film f = new Film("Film inconnu", 999);
        try {
            f.save();
            fail("Une exception devait avoir lieu !");
        } catch (RealisateurAbsentException | SQLException e) {
            assertTrue(true);
        }
    }

    @Test
    public void findByRealisateurTest_OK() throws SQLException {
        Personne p = Personne.findByNom("Scott");
        ArrayList<Film> films = (ArrayList<Film>) Film.findByRealisateur(p);
        assertEquals(2, films.size());
        assertEquals("Les as de la jungle", films.get(0).getTitre());
        assertEquals("Les as, le retour", films.get(1).getTitre());
    }

    @Test
    public void findByRealisateurTest_NOK() {
        Personne p = new Personne("Gérome", "Marchal");
        try {
            p.save();
            Film.findByRealisateur(p);
            fail("Une exception devait avoir lieu !");
        } catch (RealisateurAbsentException | SQLException e) {
            assertTrue(true);
        }
    }

    @Test
    public void findByRealisateurTest_PasDansBase() throws SQLException {
        Personne p = new Personne("Gérome", "Marchal");
        try {
            Film.findByRealisateur(p);
            fail("Une exception devait avoir lieu !");
        } catch (RealisateurAbsentException | SQLException e) {
        assertTrue(true);
        }
    }


    @After
    public void tearDown() throws SQLException {
        Film.deleteTable();
        Personne.deleteTable();
    }
}
