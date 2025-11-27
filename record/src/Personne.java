import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Personne {
    private int id;
    private String nom;
    private String prenom;

    public Personne(String nom, String prenom) {
        this.id = -1;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public static Personne findById(int id) throws SQLException {
        ArrayList<Personne> list = new ArrayList<>();
        String SQLPrep = "SELECT * FROM Personne WHERE id= ?;";
        PreparedStatement prep1 = DBConnection.getConnection().prepareStatement(SQLPrep);
        prep1.setInt(1, id);
        prep1.execute();
        ResultSet rs = prep1.getResultSet();
        rs.next();
        String nom = rs.getString("nom");
        String prenom = rs.getString("prenom");
        int idp = rs.getInt("id");
        Personne p = new Personne(nom, prenom);
        p.id = idp;
        return p;
    }

    public static Personne findByNom(String nom) throws SQLException {
        ArrayList<Personne> list = new ArrayList<>();
        String SQLPrep = "SELECT * FROM Personne WHERE nom=?;";
        PreparedStatement prep1 = DBConnection.getConnection().prepareStatement(SQLPrep);
        prep1.setString(1, nom);
        prep1.execute();
        ResultSet rs = prep1.getResultSet();
        rs.next();
        String nomp = rs.getString("nom");
        String prenom = rs.getString("prenom");
        int idp = rs.getInt("id");
        Personne p = new Personne(nomp, prenom);
        p.id = idp;
        return p;
    }

    public static ArrayList<Personne> findAll() throws SQLException {
        ArrayList<Personne> list = new ArrayList<>();
        String SQLPrep = "SELECT * FROM Personne;";
        PreparedStatement prep1 = DBConnection.getConnection().prepareStatement(SQLPrep);
        prep1.execute();
        ResultSet rs = prep1.getResultSet();
        while (rs.next()) {
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            int id = rs.getInt("id");
            Personne p = new Personne(nom, prenom);
            p.id = id;
            list.add(p);
        }
        return list;
    }
    public static void createTable() throws SQLException {
        String createString = "CREATE TABLE Personne ( "
                + "ID INTEGER  AUTO_INCREMENT, " + "NOM varchar(40) NOT NULL, "
                + "PRENOM varchar(40) NOT NULL, " + "PRIMARY KEY (ID))";
        Statement stmt = DBConnection.getConnection().createStatement();
        stmt.executeUpdate(createString);
    }
    public static void deleteTable() throws SQLException {
        String drop = "DROP TABLE Personne";
        Statement stmt = DBConnection.getConnection().createStatement();
        stmt.executeUpdate(drop);

    }
    public String toString(){
        return nom + " " + prenom;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void save() {
    }
}
