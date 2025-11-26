import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @AfterEach
    public void tearDown() throws SQLException {
        Personne.deleteTable();
    }
}
