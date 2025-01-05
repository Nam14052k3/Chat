
package sendfile.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    private DatabaseConnection() {

    }

    public void connectToDatabase() throws SQLException {
        String server = "DESKTOP-7EOCORJ\\SQLEXPRESS";
        String port = "1433";
        String database = "ChatApp";
        String userName = "sa";
        String password = "123";
        String connectionUrl = "jdbc:sqlserver://DESKTOP-7EOCORJ\\SQLEXPRESS:1433;databaseName=ChatApp;encrypt=false";
        connection = java.sql.DriverManager.getConnection(connectionUrl, userName, password);



        // Load the SQL Server JDBC Driver
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQL Server JDBC Driver not found.", e);
        }

        // Establish the connection
        connection = java.sql.DriverManager.getConnection(connectionUrl, userName, password);
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}


