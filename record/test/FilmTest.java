import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class FilmTest {

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
        Film.createTable();
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

    }


    @After
    public void tearDown() throws SQLException {
        Personne.deleteTable();
    }
}
