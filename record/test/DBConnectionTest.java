import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DBConnectionTest {

    @Test
    public void testConnection() throws SQLException {
        Connection conn = DBConnection.getConnection();
        assertTrue(conn instanceof java.sql.Connection);
    }

    @Test
    public void test2Connection() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Connection conn2 = DBConnection.getConnection();

        assertTrue(conn == conn2);

    }

}
