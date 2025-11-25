import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {
    Properties connectionProps = new Properties();
    String userName = "root";
    String password = "";
    String serverName = "127.0.0.1";
    String portNumber = "3306";
    private String tableName = "testpersonne";
    private static String dbName = "testpersonne";
    private static Connection connection;
    private Statement statement;
    
    private DBConnection() throws SQLException{
        Properties prop = new Properties();
        connectionProps.put("user",userName);
        connectionProps.put("password", password);
        String urlDB = "jdbc:mysql://" + serverName + ":";
        urlDB += portNumber + "/" + dbName;
        this.connection = DriverManager.getConnection(urlDB, connectionProps);
    }

    public static synchronized Connection getConnection() throws SQLException {
        if(connection == null){
            new DBConnection();
        }
        return connection;
    }
    public static synchronized void setDbName(String nomDB) throws SQLException {
        if(nomDB == null || nomDB!=dbName){
            dbName = nomDB;
            connection = null;
        }
    }
}
