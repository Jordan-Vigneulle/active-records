import java.sql.*;

public class Film {
    private int id;
    private String titre;
    private int id_rea;

    public Film(int id, String titre, int id_rea) {
        this.id = id;
        this.titre = titre;
        this.id_rea = id_rea;
    }

    public static Film findById(int id) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "select * from film where id = ?";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return new Film(rs.getInt("id"), rs.getString("titre"), rs.getInt("id_rea"));
        }
        return null;
    }

    public Personne getRealisateur() throws SQLException {
        return Personne.findById(this.id_rea);
    }

    public static void createTable() throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "CREATE TABLE `Film` (\n" +
                "  `id` int(11) NOT NULL,\n" +
                "  `titre` varchar(40) NOT NULL,\n" +
                "  `id_rea` int(11) DEFAULT NULL\n" +
                ")";
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
    }

    public static void deleteTable() throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "DROP TABLE IF EXISTS `Film`";
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
    }
}
