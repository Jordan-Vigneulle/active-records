import java.sql.*;

public class Film {
    private int fid;
    private String ftitre;
    private int fid_rea;

    public Film(String titre, int id_rea) {
        this.fid = -1;
        this.ftitre = titre;
        this.fid_rea = id_rea;
    }

    public static Film findById(int id) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "select * from film where id = ?";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            Film f = new Film( rs.getString("titre"), rs.getInt("id_rea"));
            f.fid = rs.getInt("id");
            return f;
        }

        return null;
    }

    public Personne getRealisateur() throws SQLException {
        return Personne.findById(this.fid_rea);
    }

    public static void createTable() throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "CREATE TABLE `Film` (\n" +
                "  `id` int(11)  AUTO_INCREMENT PRIMARy KEY,\n" +
                "  `titre` varchar(40) NOT NULL,\n" +
                "  `id_rea` int(11) DEFAULT NULL,\n" +
                "CONSTRAINT fk_id_rea FOREIGN KEY (id_rea) REFERENCES Film(id))";
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
    }

    public static void deleteTable() throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "DROP TABLE IF EXISTS `Film`";
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
    }

    public void save() throws SQLException {
        if(Personne.findById(this.fid_rea).getId() == -1) {
            throw new RealisateurAbsentException("");
        }else{
            if(this.fid == -1) {
                String SQLPrep = "INSERT INTO Film (titre, id_rea) VALUES (?,?);";
                PreparedStatement prep = DBConnection.getConnection().prepareStatement(SQLPrep, Statement.RETURN_GENERATED_KEYS);
                prep.setString(1, this.ftitre);
                prep.setInt(2, this.fid_rea);
                prep.executeUpdate();
            }else{
                String update = "Update Personne set nom = ?, prenom = ? where id = ?";
                PreparedStatement stmt = DBConnection.getConnection().prepareStatement(update);
                stmt.setString(1, this.ftitre);
                stmt.setInt(2, this.fid_rea);
                stmt.setInt(3, this.fid);
                stmt.executeUpdate(update);
            }
        }

    }
}
