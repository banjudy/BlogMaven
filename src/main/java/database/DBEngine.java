package database;
import java.sql.*;
import java.util.Properties;

public class DBEngine {

    public Connection connection;

    // Singleton
    public static DBEngine dbEngine = null;

    public static DBEngine getInstance() {
        if (dbEngine == null) {
            dbEngine = new DBEngine();
            dbEngine.connection = dbEngine.connect();
        }
        return dbEngine;
    }

    public DBEngine() {
    }

    public boolean isConnected(){
        return (connection != null);
    }

    private Connection connect(){
        String url = "jdbc:mysql://localhost:3306/blogdb" +
                "?useUnicode=yes&characterEncoding=UTF-8";

        Properties properties = new Properties();
        properties.put("user", System.getenv("DB_USER"));
        properties.put("password", System.getenv("DB_PASSWORD"));

        try {
            return DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            System.out.println("sql error");
            return null;
        }
    }
}
